package presenter;

import utils.SearchResult;
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
        scoreModel.addListener(new ModelListener() {
            @Override
            public void hasFinished() {
                    showNewScore();
                }
        });
        actualTitle = pagePresenter.getLastSelectedResultTitle();
        actualRankedSeries = new RankedSeries(actualTitle,score);
        scoreModel.updateScore(actualTitle,score);
        ratedSeries = scoreModel.getRatedSeries();
    }
    private void showNewScore(){
        createComboBoxRatedSeries();
        updateScoreComboBox();
    }
    private void createComboBoxRatedSeries(){
        scoreView.getRatedSeriesComboBox().setModel(new DefaultComboBoxModel());
    }
    public void updateScoreComboBox(){
        ratedSeries = scoreModel.getRatedSeries();
        sortRatedSeries(ratedSeries);

        for(RankedSeries rankedSeries : ratedSeries){
            scoreView.getRatedSeriesComboBox().addItem(rankedSeries);
        }
    }
    private void sortRatedSeries(ArrayList<RankedSeries> ratedSeries){
        ratedSeries.sort((rs1, rs2) -> Integer.compare(rs1.getScore(), rs2.getScore()));
    }
    public ArrayList<RankedSeries> getRatedSeries(){
        return ratedSeries;
    }
    public void setPagePresenter(PagePresenter pagePresenter){
        this.pagePresenter = pagePresenter;
    }
    public void onEventComboBoxRankSelected(RankedSeries selectedRankedSeries){
        System.out.println("Seleccionaste un elemento rankeado :)");
    }
}
