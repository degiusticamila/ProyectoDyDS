package UnitTests;

import model.ModelListener;
import model.SearchExecutor;
import model.SearchModel;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SearchModelUnitTest {
    private SearchModel searchModel;
    private SearchExecutor mockSearchExecutor;
    private ModelListener mockListener;
    @Before
    public void setUp(){
        mockSearchExecutor = mock(SearchExecutor.class);
        searchModel = new SearchModel(mockSearchExecutor);

        mockListener = mock(ModelListener.class);
        searchModel.addListener(mockListener);
    }
    @Test
    public void testSearchInWikipedia() {
        String termToSearch = "Gilmore Girls";
        String mockResponseBody = "Mock response";
        Response<String> mockResponse = Response.success(mockResponseBody);

        when(mockSearchExecutor.executeSearch(termToSearch)).thenReturn(mockResponse);

        searchModel.searchInWikipedia(termToSearch);

        //me fijo que se llame a ejecutar la busqueda del mock de SearchExecutor
        verify(mockSearchExecutor).executeSearch(termToSearch);

        verify(mockListener).hasFinished();
        assertEquals(mockResponse, searchModel.getLastSearchResponse());
    }
    @Test
    public void emptyTestSearchInWikipedia(){
        String termToSearch = " ";
        String mockResponseBody = "Mock response";
        Response<String> mockResponse = Response.success(mockResponseBody);

        when(mockSearchExecutor.executeSearch(termToSearch)).thenReturn(mockResponse);

        searchModel.searchInWikipedia(termToSearch);

        verify(mockSearchExecutor).executeSearch(termToSearch);

        verify(mockListener).hasFinished();
        assertEquals(mockResponse, searchModel.getLastSearchResponse());
    }
}
