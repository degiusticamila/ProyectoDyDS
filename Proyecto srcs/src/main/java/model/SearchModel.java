package model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dyds.tvseriesinfo.fulllogic.SearchResult;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class SearchModel{
    private WikipediaSearchAPI searchAPI;
    private Response<String> callForSearchResponse;
    private JsonArray jsonResults;
    private ArrayList<SearchModelListener> listeners = new ArrayList<>();
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
        System.out.println("Trae de la api el termino ");
        //Una vez que termina de buscar, notifica que termino!
        notifySearchFinishedListener();
        System.out.println("Notifica al presentador que ya termino");

    }
    public Response<String> getLastSearchResponse(){
        return callForSearchResponse;
    }
    private void notifySearchFinishedListener(){
        for(SearchModelListener l :listeners){
            l.searchFinished();
            System.out.println("searchModelListener "+l);
        }
        //System.out.println("searchModelListener"+searchModelListener);
        //searchModelListener.searchFinished();
    }

    public void addListener(SearchModelListener listener) {
        this.listeners.add(listener);
    }

}
