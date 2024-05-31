package model;

import dyds.tvseriesinfo.fulllogic.SearchResult;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.ArrayList;

public class PageModel {
    private WikipediaPageAPI pageAPI;
    private Response<String> callForPageResponse;
    private ArrayList<ModelListener> pageListeners = new ArrayList<>();
    public PageModel(){
        createWikiPageAPI();
    }
    private void createWikiPageAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        pageAPI = retrofit.create(WikipediaPageAPI.class);
    }
    public void calculateCallForPageResponse(SearchResult sr){
        try {
            callForPageResponse = pageAPI.getExtractByPageID(sr.pageID).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        notifyPageFinishedListener();
    }
    public Response<String> getLastPageResponse(){
        return callForPageResponse;
    }
    private void notifyPageFinishedListener(){
        for(ModelListener l : pageListeners){
            l.hasFinished();
        }
    }
    public void addListener(ModelListener listener) {
        this.pageListeners.add(listener);
    }
}
