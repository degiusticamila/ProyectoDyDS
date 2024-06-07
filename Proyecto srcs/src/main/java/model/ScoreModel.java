package model;

import java.util.ArrayList;

public class ScoreModel {
    private ArrayList<ModelListener> scoreModelListeners = new ArrayList<>();
    private ArrayList<RankedSeries> ratedSeriesModel;
    private DataBaseInterface database;

    public ScoreModel(){
        database = new DataBaseImpl();
    }
    public void addListener(ModelListener listener) {
        this.scoreModelListeners.add(listener);
    }
    private void notifySaveLocallyFinished(){
        for(ModelListener l : scoreModelListeners){
            l.hasFinished();
        }
    }
    public void updateScore(String titleSelected, Integer score){
        database.saveScore(titleSelected,score);
        notifySaveLocallyFinished();
    }
    public ArrayList<RankedSeries> getRatedSeries(){
        Object[] ratedTitleArray;

        ratedTitleArray = database.getTitlesScores().stream().sorted().toArray();
        ratedSeriesModel = new ArrayList<>();

        for(Object obj: ratedTitleArray){
            String score = database.getScores((String) obj);
            Integer parseScore = Integer.parseInt(score);

            RankedSeries rankedSeries = new RankedSeries((String) obj,parseScore);
            rankedSeries.setLastModificationDate(database.getDates((String)obj));
            ratedSeriesModel.add(rankedSeries);
        }
        return ratedSeriesModel;
    }
}
