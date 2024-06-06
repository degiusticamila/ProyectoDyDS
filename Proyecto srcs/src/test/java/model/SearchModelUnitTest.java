package model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SearchModelUnitTest {
   private SearchModel searchModeltoTest;

   @Mock
   private WikipediaSearchAPI wikipediaAPIMock;

   @Before
   public void setUp() {
      wikipediaAPIMock = mock(WikipediaSearchAPI.class);
      searchModeltoTest = new SearchModel(wikipediaAPIMock);
   }
   @Test
   public void testSearchWithEmptyTerm() {

   }
}