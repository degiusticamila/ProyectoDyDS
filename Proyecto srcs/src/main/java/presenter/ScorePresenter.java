package presenter;

import model.ModelListener;
import utils.ScoreItem;
import view.ScoreView;
import view.SearchView;
import model.ScoreModel;
import javax.swing.*;

public class ScorePresenter {
    private SearchView searchView;
    private ScoreView scoreView;
    private ScoreModel scoreModel;
    private String actualTitle;
    private PagePresenter pagePresenter;
    public ScorePresenter(SearchView searchView, ScoreView scoreView, ScoreModel scoreModel){
        this.searchView = searchView;
        this.scoreView = scoreView;
        this.scoreModel = scoreModel;

    }
    public void onEventClickedScoreButton(Integer score){
        System.out.println("score model: "+scoreModel);
        scoreModel.addListener(new ModelListener() {
            @Override
            public void hasFinished() {
                showNewScore(actualTitle);
            }
        });
        actualTitle = pagePresenter.getLastSelectedResultTitle();
        System.out.println("titulo a puntuar: "+actualTitle);
        scoreModel.updateScore(actualTitle,score);
    }
    private void showNewScore(String actualTitle){
        ScoreItem scoreItem = new ScoreItem(actualTitle,"/utils/image-icon.png");
        scoreView.getRatedSeriesComboBox().addItem(scoreItem);

    }
    public void setPagePresenter(PagePresenter pagePresenter){
        this.pagePresenter = pagePresenter;
    }
}
