package model;

import java.util.ArrayList;

public class DataBaseModel {
    private ArrayList<ModelListener> dataBaseListeners = new ArrayList<>();
    public void saveLocally(String selectedResultTitle,String text){
        DataBase.saveInfo(selectedResultTitle.replace("'", "`"), text);  //Dont forget the ' sql problem
        notifySaveLocallyFinished();
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
