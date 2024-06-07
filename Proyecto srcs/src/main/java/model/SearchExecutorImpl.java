package model;

import retrofit2.Response;

import java.io.IOException;

public class SearchExecutorImpl implements SearchExecutor{
    public WikipediaSearchAPI searchAPI;

    public SearchExecutorImpl(WikipediaSearchAPI searchAPI){
        this.searchAPI = searchAPI;
    }
    @Override
    public Response<String> executeSearch(String termToSearch) {
        try {
            return searchAPI.searchForTerm(termToSearch + " (Tv series) articletopic:\"television\"").execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
