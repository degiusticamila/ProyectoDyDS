package presenter;

import model.DataBase;
import model.DataBaseModel;
import view.StorageView;

import javax.swing.*;

public class StoragePresenter {
    private StorageView storageView;
    private DataBaseModel dataBaseModel;
    private Object[] savedSeries;
    private String textSaved;

    public StoragePresenter(StorageView storageView, DataBaseModel dataBaseModel){
        this.storageView = storageView;
        this.dataBaseModel = dataBaseModel;
    }
    public void onEventClickedSeriesComboBox(String selectedTitle){
        storageView.setWorkingStatus();
        textSaved = DataBase.getExtract(selectedTitle);
        //savedSeries = dataBaseModel.getSavedSeries();
        storageView.setWatingStatus();
        showInSavedSeriesTextPane(textSaved);
    }
    public void showSavedSeries(){
        storageView.getSeriesComboBox().setModel(new DefaultComboBoxModel(dataBaseModel.getSavedSeries()));
    }
    private void showInSavedSeriesTextPane(String textSaved){
        storageView.getSavedSeriesTextPane().setContentType("text/html");
        storageView.getSavedSeriesTextPane().setText(textSaved);
    }
}
