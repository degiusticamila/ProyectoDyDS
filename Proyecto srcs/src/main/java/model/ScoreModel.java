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
        notifySaveLocallyFinished();
    }
    public ArrayList<RankedSeries> getRatedSeries(){
        Object[] ratedTitleArray;

        ratedTitleArray = DataBase.getTitlesScores().stream().sorted().toArray();
        ratedSeriesModel = new ArrayList<>();
        for(Object obj: ratedTitleArray){
            String score = DataBase.getScores((String) obj);
            Integer parseScore = Integer.parseInt(score);

            RankedSeries rankedSeries = new RankedSeries((String) obj,parseScore);
            rankedSeries.setLastModificationDate(DataBase.getDates((String)obj));
            ratedSeriesModel.add(rankedSeries);
        }
        return ratedSeriesModel;
    }
}
