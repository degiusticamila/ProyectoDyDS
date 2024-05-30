package model;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.ArrayList;

public class SearchModel{
    private WikipediaSearchAPI searchAPI;
    private Response<String> callForSearchResponse;
    private ArrayList<ModelListener> listeners = new ArrayList<>();
    public SearchModel(){
        createWikiSearchAPI();
    }
    private void createWikiSearchAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

         searchAPI = retrofit.create(WikipediaSearchAPI.class);
    }
    public void searchInWikipedia(String termToSearch){
        System.out.println("Entra al metodo buscar en Wikipedia");
        try {
            callForSearchResponse = searchAPI.searchForTerm(termToSearch + " (Tv series) articletopic:\"television\"").execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        notifySearchFinishedListener();
    }
    public Response<String> getLastSearchResponse(){
        return callForSearchResponse;
    }
    private void notifySearchFinishedListener(){
        for(ModelListener l :listeners){
            l.searchFinished();
            System.out.println("ModelListener "+l);
        }
    }
    public void addListener(ModelListener listener) {
        this.listeners.add(listener);
    }

}
