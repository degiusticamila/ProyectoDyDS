package model;

import retrofit2.Response;

public interface SearchExecutor {
    Response<String> executeSearch(String termToSearch);
}
