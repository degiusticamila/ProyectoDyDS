package model;

import java.util.ArrayList;

public class SaveModel {
    private ArrayList<ModelListener> dataBaseListeners;
    private DataBaseInterface dataBase;
    public SaveModel(){
        dataBaseListeners = new ArrayList<>();
        dataBase = new DataBaseImpl();
    }
    public void saveLocally(String selectedResultTitle,String text){
        dataBase.saveInfo(selectedResultTitle.replace("'", "`"), text);
        notifySaveLocallyFinished();
    }
    public Object[] getSavedSeries(){
        Object[] savedSeriesArray;
        savedSeriesArray = dataBase.getTitles().stream().sorted().toArray();
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
