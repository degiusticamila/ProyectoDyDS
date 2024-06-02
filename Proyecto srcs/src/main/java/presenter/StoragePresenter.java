package presenter;

import model.DataBase;
import model.GetDBModel;
import model.ModelListener;
import model.SaveModel;
import view.StorageView;

import javax.swing.*;

public class StoragePresenter {
    private StorageView storageView;
    private SaveModel saveModel;
    private GetDBModel dbModel;
    private Object[] savedSeries;
    private String textSaved;

    public StoragePresenter(StorageView storageView, SaveModel saveModel,GetDBModel dbModel){
        this.storageView = storageView;
        this.saveModel = saveModel;
        this.dbModel = dbModel;
    }
    public void onEventClickedSeriesComboBox(String selectedTitle){
        dbModel.addListener(new ModelListener() {
            @Override
            public void hasFinished() {
                showInSavedSeriesTextPane();
            }
        });
        storageView.setWorkingStatus();
        dbModel.getExtractText(selectedTitle);
        storageView.setWatingStatus();

    }
    public void showSavedSeries(){
        storageView.getSeriesComboBox().setModel(new DefaultComboBoxModel(saveModel.getSavedSeries()));
    }
    private void showInSavedSeriesTextPane(){
        textSaved = dbModel.getLastExtract();
        System.out.println("text saved: "+textSaved);
        storageView.getSavedSeriesTextPane().setContentType("text/html");
        storageView.getSavedSeriesTextPane().setText(textSaved);
    }
}
