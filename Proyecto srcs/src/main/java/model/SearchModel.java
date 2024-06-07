package model;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.ArrayList;

public class SearchModel{
    private WikipediaSearchAPI searchAPI;
    private Response<String> callForSearchResponse;
    private ArrayList<ModelListener> listeners;
    public SearchModel(WikipediaSearchAPI searchAPI){
        listeners = new ArrayList<>();
        this.searchAPI = searchAPI;
    }

    public void searchInWikipedia(String termToSearch){
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
            l.hasFinished();
        }
    }
    public void addListener(ModelListener listener) {
        this.listeners.add(listener);
    }
}
