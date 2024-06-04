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
    public ScorePresenter(SearchView searchView, ScoreView scoreView, ScoreModel scoreModel){
        this.searchView = searchView;
        this.scoreView = scoreView;
        this.scoreModel = scoreModel;
    }
    public void onEventClickedScoreButton(Integer score, SearchResult selectedSeries){
        System.out.println("score model: "+scoreModel);
        scoreModel.addListener(new ModelListener() {
            @Override
            public void hasFinished() {
                showNewScore(selectedSeries);
            }
        });
       // if(ratedSeries == null){
         //   createRatedSeriesList();
        //}
        //ratedSeries = getRatedSeries();
        ratedSeries = scoreModel.getRatedSeries();
        actualTitle = pagePresenter.getLastSelectedResultTitle();
        actualRankedSeries = new RankedSeries(actualTitle,score);
        scoreModel.updateScore(actualTitle,score);
    }
    private void showNewScore(SearchResult selectedSeries){
       updateScoreComboBox();
        addRatedSeries(actualRankedSeries);
        String date = actualRankedSeries.getLastModificationDateFormatted();
        String title = actualRankedSeries.getSeriesTitle();
        Integer score = actualRankedSeries.getScore();

        scoreView.getRatedSeriesComboBox().addItem(title+" "+ score +" "+ date);
    }
    public void updateScoreComboBox(){
        scoreView.getRatedSeriesComboBox().setModel(new DefaultComboBoxModel());
        ratedSeries = scoreModel.getRatedSeries();
        System.out.println("rated series: "+ratedSeries);
        for(RankedSeries rankedSeries : ratedSeries){
            scoreView.getRatedSeriesComboBox().addItem(rankedSeries.getSeriesTitle()+" "+rankedSeries.getScore()+" "+rankedSeries.getLastModificationDateFormatted());
        }
    }
    private void createRatedSeriesList(){
        ratedSeries = new ArrayList<>();
    }
    private RankedSeries addRatedSeries(RankedSeries rankedSeries){
        ratedSeries.add(rankedSeries);
        return rankedSeries;
    }
    private ArrayList<RankedSeries> getRatedSeries(){
        return ratedSeries;
    }
    public void setPagePresenter(PagePresenter pagePresenter){
        this.pagePresenter = pagePresenter;
    }
}
