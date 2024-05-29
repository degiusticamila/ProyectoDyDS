package presenter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dyds.tvseriesinfo.fulllogic.SearchResult;
import model.SearchModelListener;
import view.SearchView;
import model.SearchModel;
import retrofit2.Response;
import utils.SearchPresenterUtilities;
import java.awt.*;
import java.util.Iterator;

public class SearchPresenter {
    private SearchView view;
    private SearchModel searchModel;


    public SearchPresenter(SearchView view, SearchModel model) {
        this.view = view;
        this.searchModel = model;
    }
    // public void initListeners(){
    //   searchModel.addListener(new SearchModelListener() {
    //     @Override
    //   public void searchFinished() {
    //     showSearchResult();
    //}
    //});

    //view.getGoSearchButton().addActionListener(actionEvent ->{
    //  String termToSearch = view.getSeriesToSearchTextField().getText();
    //esto deberia estar en la vista y el presenter le pide el textFiel
    //presenter.onEvent....
    //onEventClickedGoButtonToSearch(termToSearch);
    //});
    // }

    public void onEventClickedGoButtonToSearch() {
        searchModel.addListener(new SearchModelListener() {
            @Override
            public void searchFinished() {
                showSearchResult();
            }
        });
        String termToSearch = view.getSeriesToSearchTextField().getText();
        Thread taskThread = new Thread(() -> {
            view.setWorkingStatus();
            view.getCurrentSearchTextPane().setContentType("text/html");
            searchModel.searchInWikipedia(termToSearch);
            view.setWatingStatus();
        });
        taskThread.start();

    }

    private void showSearchResult() {
        Response<String> lastSearchResponse = searchModel.getLastSearchResponse();
        JsonArray jsonResults = SearchPresenterUtilities.calculateJSonObjects(lastSearchResponse);
        view.createJPopMenu();
        Iterable<SearchResult> searchResults = SearchPresenterUtilities.calculateSearchResults(jsonResults);
        addSearchResults(searchResults);
        view.getPopupMenu().show(view.getSeriesToSearchTextField(), view.getSeriesToSearchTextField().getX(), view.getSeriesToSearchTextField().getY());
    }
    private void addSearchResults(Iterable<SearchResult> searchResults){
        for (SearchResult sr : searchResults){
            view.getPopupMenu().add(sr);
        }
    }

    public void onEventPopupSelected() {

    }


}
