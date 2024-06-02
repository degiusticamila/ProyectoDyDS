package model;

import java.util.ArrayList;

public class SaveModel {
    private ArrayList<ModelListener> dataBaseListeners = new ArrayList<>();
    public void saveLocally(String selectedResultTitle,String text){
        System.out.println("titulo "+selectedResultTitle);
        System.out.println("text "+text);
        DataBase.saveInfo(selectedResultTitle.replace("'", "`"), text);  //Dont forget the ' sql problem
        notifySaveLocallyFinished();
    }
    //No se si esto es muy correcto que digamos.
    // tampoco se si deberia agregar un listener para esto y notificar(creo que si).
    public Object[] getSavedSeries(){
        Object[] savedSeriesArray;
        savedSeriesArray = DataBase.getTitles().stream().sorted().toArray();
        //esto no le gusta :(
        //notifySaveLocallyFinished();
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
