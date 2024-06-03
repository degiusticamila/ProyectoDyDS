package presenter;

import model.ModelListener;
import view.ScoreView;
import view.SearchView;
import model.ScoreModel;

public class ScorePresenter {
    private SearchView searchView;
    private ScoreView scoreView;
    private ScoreModel scoreModel;

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
                showNewScore();
            }
        });
        String actualTitle = pagePresenter.getLastSelectedResultTitle();
        System.out.println("titulo a puntuar: "+actualTitle);
        scoreModel.updateScore(actualTitle,score);
    }
    private void showNewScore(){
        //agregar una insignia de que la serie fue calificada.
        //agregar la serie calificada al desplegable de la score view.
        // no se que mas :P ya basta Gotti
    }
    public void setPagePresenter(PagePresenter pagePresenter){
        this.pagePresenter = pagePresenter;
    }
}
