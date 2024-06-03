package model;

import javax.swing.*;
import java.util.ArrayList;

public class ScoreModel {
    private ArrayList<ModelListener> scoreModelListeners = new ArrayList<>();
    private ArrayList<RankedSeries> ratedSeriesModel;
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
        System.out.println("puntaje guardado"+"\n");
        notifySaveLocallyFinished();
        System.out.println("notificamos al presentador que ya terminanos de guardar el puntaje"+"\n");
    }
    public ArrayList<RankedSeries> getRatedSeries(){
        Object[] ratedTitleArray;
        ratedSeriesModel = new ArrayList<>();
        //tengo todos los titulos que tienen una calificacion asociada.
        ratedTitleArray = DataBase.getTitlesScores().stream().sorted().toArray();

        //ahora puedo pedirle su puntaje y su fecha. ?

        for(Object obj: ratedTitleArray){
            RankedSeries rankedSeries = new RankedSeries((String) obj,DataBase.getScores((String)obj).intValue());
            rankedSeries.setLastModificationDate(DataBase.getDates((String)obj));
            ratedSeriesModel.add(rankedSeries);
        }

        return ratedSeriesModel;
    }
}
