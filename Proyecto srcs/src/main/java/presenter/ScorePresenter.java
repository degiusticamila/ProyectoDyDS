package presenter;

import dyds.tvseriesinfo.fulllogic.SearchResult;
import model.ModelListener;
import model.RankedSeries;
import view.ScoreView;
import view.SearchView;
import model.ScoreModel;

import javax.swing.*;
import java.util.ArrayList;

public class ScorePresenter {
    private SearchView searchView;
    private ScoreView scoreView;
    private ScoreModel scoreModel;
    private String actualTitle;
    private ArrayList<RankedSeries> ratedSeries;
    private PagePresenter pagePresenter;
    private RankedSeries actualRankedSeries;
    private boolean isListenerRegistered = false;
    public ScorePresenter(SearchView searchView, ScoreView scoreView, ScoreModel scoreModel){
        this.searchView = searchView;
        this.scoreView = scoreView;
        this.scoreModel = scoreModel;
    }
    public void onEventClickedScoreButton(Integer score, SearchResult selectedSeries){
        scoreModel.addListener(new ModelListener() {
            @Override
            public void hasFinished() {
                    showNewScore(selectedSeries);
                }
        });
        //ratedSeries = scoreModel.getRatedSeries();
        actualTitle = pagePresenter.getLastSelectedResultTitle();
        actualRankedSeries = new RankedSeries(actualTitle,score);
        scoreModel.updateScore(actualTitle,score);
        ratedSeries = scoreModel.getRatedSeries();
    }
    private void showNewScore(SearchResult selectedSeries){
       updateScoreComboBox();
        //addRatedSeries(actualRankedSeries);
        //String date = actualRankedSeries.getLastModificationDateFormatted();
        //String title = actualRankedSeries.getSeriesTitle();
        //Integer score = actualRankedSeries.getScore();
        //scoreView.getRatedSeriesComboBox().addItem(title+" "+ score +" "+ date);
    }
    public void updateScoreComboBox(){
        scoreView.getRatedSeriesComboBox().setModel(new DefaultComboBoxModel());
        ratedSeries = scoreModel.getRatedSeries();
        sortRatedSeries(ratedSeries);
        for(RankedSeries rankedSeries : ratedSeries){
            scoreView.getRatedSeriesComboBox().addItem(rankedSeries.getSeriesTitle()+" "+rankedSeries.getScore()+" "+rankedSeries.getLastModificationDateFormatted());
        }
    }
    private void sortRatedSeries(ArrayList<RankedSeries> ratedSeries){
        // Ordenar de menor a mayor puntaje
        ratedSeries.sort((rs1, rs2) -> Integer.compare(rs1.getScore(), rs2.getScore()));
    }
    private void createRatedSeriesList(){
        ratedSeries = new ArrayList<>();
    }
    private void addRatedSeries(RankedSeries rankedSeries){
        ratedSeries.add(rankedSeries);
    }

    public ArrayList<RankedSeries> getRatedSeries(){
        return ratedSeries;
    }
    public void setPagePresenter(PagePresenter pagePresenter){
        this.pagePresenter = pagePresenter;
    }
    public Integer getScoreModel(String title){
        return scoreModel.getScore(title);
    }
}
