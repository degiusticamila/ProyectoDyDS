package Mocks;

import model.SearchExecutor;
import retrofit2.Response;

public class MockSearchExecutor implements SearchExecutor {
    @Override
    public Response<String> executeSearch(String termToSearch) {
        // Crear una respuesta simulada para las pruebas
        return Response.success("Mock response");
    }
}
