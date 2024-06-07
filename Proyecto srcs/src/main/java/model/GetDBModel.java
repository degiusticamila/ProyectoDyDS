package model;

import java.util.ArrayList;

public class GetDBModel {
    private String lastExtract;
    private ArrayList<ModelListener> listeners = new ArrayList<>();
    public void addListener(ModelListener listener) {
        this.listeners.add(listener);
    }
    private void notifyExtractFinished(){
        for(ModelListener l : listeners){
            l.hasFinished();
        }
    }
    public void getExtractText(String selectedTitle){
        lastExtract = DataBaseImpl.getExtract(selectedTitle);
        notifyExtractFinished();
    }
    public String getLastExtract(){
        return lastExtract;
    }
}
