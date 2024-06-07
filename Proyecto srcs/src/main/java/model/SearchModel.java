package model;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.ArrayList;

public class SearchModel{
    private SearchExecutor searchExecutor;
    private Response<String> callForSearchResponse;
    private ArrayList<ModelListener> listeners;

   public SearchModel(SearchExecutor searchExecutor){
       listeners = new ArrayList<>();
       this.searchExecutor = searchExecutor;
   }
    public void searchInWikipedia(String termToSearch){
        callForSearchResponse = searchExecutor.executeSearch(termToSearch);
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
