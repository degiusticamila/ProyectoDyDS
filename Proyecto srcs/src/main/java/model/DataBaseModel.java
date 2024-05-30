package model;

import java.util.ArrayList;

public class DataBaseModel {
    private ArrayList<ModelListener> dataBaseListeners = new ArrayList<>();

    public void saveLocally(){
        //DataBase.saveInfo(selectedResultTitle.replace("'", "`"), text);  //Dont forget the ' sql problem
        notifySaveLocallyFinished();
    }
    public void addListener(ModelListener listener) {
        this.dataBaseListeners.add(listener);
    }
    private void notifySaveLocallyFinished(){
        for(ModelListener l : dataBaseListeners){
            l.searchFinished();
        }
    }
}
