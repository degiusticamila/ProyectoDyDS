package presenter;
import com.google.gson.JsonArray;
import dyds.tvseriesinfo.fulllogic.SearchResult;
import model.ModelListener;
import view.SearchView;
import model.SearchModel;
import retrofit2.Response;
import utils.SearchPresenterUtilities;
public class SearchPresenter {
    private SearchView searchView;
    private SearchModel searchModel;

    public SearchPresenter(SearchView searchView, SearchModel searchModel) {
        this.searchView = searchView;
        this.searchModel = searchModel;
    }
    public void onEventClickedGoButtonToSearch() {
        searchModel.addListener(new ModelListener() {
            @Override
            public void hasFinished() {
                showSearchResult();
            }
        });
        String termToSearch = searchView.getSeriesToSearchTextField().getText();
        Thread taskThread = new Thread(() -> {
            searchView.setWorkingStatus();
            searchView.getCurrentSearchTextPane().setContentType("text/html");
            searchModel.searchInWikipedia(termToSearch);
            searchView.setWatingStatus();
        });
        taskThread.start();
    }
    private void showSearchResult() {
        Response<String> lastSearchResponse = searchModel.getLastSearchResponse();
        JsonArray jsonResults = SearchPresenterUtilities.calculateJSonObjects(lastSearchResponse);
        searchView.createJPopMenu();
        Iterable<SearchResult> searchResults = SearchPresenterUtilities.calculateSearchResults(jsonResults);
        searchView.createSearchResultList();
        addSearchResults(searchResults);
        searchView.showInfoPopup();
    }
    public void addSearchResults(Iterable<SearchResult> searchResults){
        //searchView.createSearchResultList();
        for (SearchResult sr : searchResults){
            searchView.getPopupMenu().add(sr);
            searchView.getSearchResultList().add(sr);
        }
    }
}
