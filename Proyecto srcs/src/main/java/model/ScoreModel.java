package model;

import utils.RankedSeries;

import java.util.ArrayList;

public class ScoreModel {
    private ArrayList<ModelListener> scoreModelListeners;
    private ArrayList<RankedSeries> ratedSeriesModel;
    private DataBaseInterface database;

    public ScoreModel(){
        scoreModelListeners = new ArrayList<>();
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
        Object[] ratedTitleArray = requestToTheDataBase();
        createRatedSeriesModel();
        createRankedSeriesElements(ratedTitleArray);
        return ratedSeriesModel;
    }
    private void createRatedSeriesModel(){
        ratedSeriesModel = new ArrayList<>();
    }
    private Object[] requestToTheDataBase(){
        Object[] ratedTitleArray;
        ratedTitleArray = database.getTitlesScores().stream().sorted().toArray();
        return ratedTitleArray;
    }
    private ArrayList<RankedSeries> createRankedSeriesElements(Object[] ratedTitleArray){
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
