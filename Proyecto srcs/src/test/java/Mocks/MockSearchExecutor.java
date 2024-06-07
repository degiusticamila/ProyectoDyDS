package Mocks;

import model.SearchExecutor;
import retrofit2.Response;

public class MockSearchExecutor implements SearchExecutor {
    @Override
    public Response<String> executeSearch(String termToSearch) {
        return Response.success("Mock response");
    }
}