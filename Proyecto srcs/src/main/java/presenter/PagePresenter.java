package presenter;

import dyds.tvseriesinfo.fulllogic.SearchResult;
import model.ModelListener;
import model.PageModel;
import view.SearchView;
import retrofit2.Response;
import utils.SearchPresenterUtilities;

public class PagePresenter {
    private PageModel pageModel;
    private SearchView searchView;

    public PagePresenter(SearchView searchView, PageModel pageModel){
        this.searchView = searchView;
        this.pageModel = pageModel;
    }
    public void onEventPopupSelected(SearchResult searchResult) {
        pageModel.addListener(new ModelListener() {
            @Override
            public void searchFinished() {
                showPageResult(searchResult);
            }
        });
        searchView.setWorkingStatus();
        pageModel.calculateCallForPageResponse(searchResult);
        searchView.setWatingStatus();
    }
    private void showPageResult(SearchResult searchResult){
        Response<String> lastCallForPageResponse = pageModel.getLastPageResponse();
        String text = SearchPresenterUtilities.calculatePageResults(lastCallForPageResponse,searchResult);
        searchView.getCurrentSearchTextPane().setText(text);
        searchView.getCurrentSearchTextPane().setCaretPosition(0);
    }

}
