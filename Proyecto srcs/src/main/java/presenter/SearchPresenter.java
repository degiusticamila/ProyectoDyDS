package presenter;
import com.google.gson.JsonArray;
import dyds.tvseriesinfo.fulllogic.SearchResult;
import model.ModelListener;
import model.RankedSeries;
import view.SearchView;
import model.SearchModel;
import retrofit2.Response;
import utils.Utilities;

import javax.swing.*;
import java.util.ArrayList;

public class SearchPresenter {
    private SearchView searchView;
    private SearchModel searchModel;
    private ScorePresenter scorePresenter;

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
        JsonArray jsonResults = Utilities.calculateJSonObjects(lastSearchResponse);
        if(jsonResults.isEmpty()) {
            searchView.notifyUnexpectedEvent("No results found.");
        }else {
            searchView.createJPopMenu();
            Iterable<SearchResult> searchResults = Utilities.calculateSearchResults(jsonResults);
            searchView.createSearchResultList();
            addSearchResults(searchResults);
            searchView.showInfoPopup();
        }
    }
    public void addSearchResults(Iterable<SearchResult> searchResults){
        for (SearchResult sr : searchResults){
           if(sr.isRated()){
                ImageIcon icon = new ImageIcon("image-icon.png");
                sr.setImageIcon(icon);
                sr.setIcon(icon);
           }
            searchView.getPopupMenu().add(sr);
            searchView.getSearchResultList().add(sr);
        }
    }
    public void setScorePresenter(ScorePresenter scorePresenter){
        this.scorePresenter = scorePresenter;
    }
}
