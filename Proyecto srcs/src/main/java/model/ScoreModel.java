package model;

import java.util.ArrayList;

public class ScoreModel {
    private ArrayList<ModelListener> scoreModelListeners = new ArrayList<>();

    public void addListener(ModelListener listener) {
        this.scoreModelListeners.add(listener);
    }
    private void notifySaveLocallyFinished(){
        for(ModelListener l : scoreModelListeners){
            l.hasFinished();
        }
    }
    public void updateScore(String titleSelected, Integer score){
        DataBase.saveScore(titleSelected,score);
        System.out.println("puntaje guardado");
        notifySaveLocallyFinished();
    }
}
