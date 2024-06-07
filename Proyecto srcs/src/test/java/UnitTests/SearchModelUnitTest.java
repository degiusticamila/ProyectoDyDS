package UnitTests;

import Mocks.MockSearchExecutor;
import Stubs.DataBaseStub;
import model.ModelListener;
import model.SearchExecutor;
import model.SearchModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SearchModelUnitTest {
    private SearchModel searchModel;
    private SearchExecutor mockSearchExecutor;
    private ModelListener mockListener;
    @Before
    public void setUp(){
        // Crear un mock de SearchExecutor
        mockSearchExecutor = mock(SearchExecutor.class);

        // Crear una instancia de SearchModel con el mock de SearchExecutor
        searchModel = new SearchModel(mockSearchExecutor);

        // Crear un mock de ModelListener y agregarlo a SearchModel
        mockListener = mock(ModelListener.class);
        searchModel.addListener(mockListener);
    }
    @Test
    public void testSearchInWikipedia() {
        String termToSearch = "Gilmore Girls";
        String mockResponseBody = "Mock response";
        Response<String> mockResponse = Response.success(mockResponseBody);

        // Configuro el mock de SearchExecutor para devolver la mock response
        when(mockSearchExecutor.executeSearch(termToSearch)).thenReturn(mockResponse);

        searchModel.searchInWikipedia(termToSearch);

        //me fijo que se llame a ejecutar la busqueda del mock de SearchExecutor
        verify(mockSearchExecutor).executeSearch(termToSearch);


        verify(mockListener).hasFinished();

        assertEquals(mockResponse, searchModel.getLastSearchResponse());
        System.out.println("Resultado obtenido :"+searchModel.getLastSearchResponse());
        System.out.println("Resultado esperado :"+ mockResponse);

    }
}
