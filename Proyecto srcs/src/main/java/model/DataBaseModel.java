package model;

import java.sql.Array;
import java.util.ArrayList;

public class DataBaseModel {
    private ArrayList<ModelListener> dataBaseListeners = new ArrayList<>();
    public void saveLocally(String selectedResultTitle,String text){
        System.out.println("titulo "+selectedResultTitle);
        System.out.println("text "+text);
        DataBase.saveInfo(selectedResultTitle.replace("'", "`"), text);  //Dont forget the ' sql problem
        notifySaveLocallyFinished();
    }
    //No se si esto es muy correcto que digamos
    public Object[] getSavedSeries(){
        Object[] savedSeriesArray;
        savedSeriesArray = DataBase.getTitles().stream().sorted().toArray();
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
