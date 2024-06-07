package model;

import java.util.ArrayList;

public class SaveModel {
    private ArrayList<ModelListener> dataBaseListeners = new ArrayList<>();
    public void saveLocally(String selectedResultTitle,String text){
        DataBaseImpl.saveInfo(selectedResultTitle.replace("'", "`"), text);  //Dont forget the ' sql problem
        notifySaveLocallyFinished();
    }
    public Object[] getSavedSeries(){
        Object[] savedSeriesArray;
        savedSeriesArray = DataBaseImpl.getTitles().stream().sorted().toArray();
        return savedSeriesArray;
    }
    public void addListener(ModelListener listener) {
        this.dataBaseListeners.add(listener);
    }
    private void notifySaveLocallyFinished(){
        for(ModelListener l : dataBaseListeners){
            l.hasFinished();
        }
    }
}
