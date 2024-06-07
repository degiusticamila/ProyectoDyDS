package model;

import java.util.ArrayList;

public class DeleteDBModel {
    private ArrayList<ModelListener> deleteListeners;
    private DataBaseInterface dataBase;
    public DeleteDBModel(){
        dataBase = new DataBaseImpl();
        deleteListeners = new ArrayList<>();
    }
    public void deleteItem(String titleSelectToDelete){
        dataBase.deleteEntry(titleSelectToDelete);
        notifyPageFinishedListener();
    }
    private void notifyPageFinishedListener(){
        for(ModelListener l : deleteListeners){
            l.hasFinished();
        }
    }
    public void addListener(ModelListener listener) {
        this.deleteListeners.add(listener);
    }
}
