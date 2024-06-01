package presenter;

import dyds.tvseriesinfo.fulllogic.SearchResult;
import model.ModelListener;
import model.PageModel;
import view.SearchView;
import retrofit2.Response;
import utils.Utilities;

public class PagePresenter {
    private PageModel pageModel;
    private SearchView searchView;
    private String lastText;
    private String lastSelectedResultTitle;


    public PagePresenter(SearchView searchView, PageModel pageModel){
        this.searchView = searchView;
        this.pageModel = pageModel;
    }
    public void onEventPopupSelected(SearchResult searchResult) {
        pageModel.addListener(new ModelListener() {
            @Override
            public void hasFinished() {
                showPageResult(searchResult);
            }
        });
        searchView.setWorkingStatus();
        pageModel.calculateCallForPageResponse(searchResult);
        searchView.setWatingStatus();

    }
    private void showPageResult(SearchResult searchResult){
        Response<String> lastCallForPageResponse = pageModel.getLastPageResponse();
        lastText = Utilities.calculatePageResults(lastCallForPageResponse,searchResult);
        lastSelectedResultTitle = searchResult.title;
        setLastSelectedResultTitle(lastSelectedResultTitle);

        searchView.getCurrentSearchTextPane().setText(lastText);
        searchView.getCurrentSearchTextPane().setCaretPosition(0);


    }
    public String getLastText(){
        return lastText;
    }
    public String getLastSelectedResultTitle(){
        return lastSelectedResultTitle;
    }
    public void setLastSelectedResultTitle(String lastSelectedResultTitle){
        this.lastSelectedResultTitle = lastSelectedResultTitle;
    }
}
