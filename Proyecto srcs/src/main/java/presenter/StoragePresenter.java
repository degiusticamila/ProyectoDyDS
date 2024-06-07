package presenter;

import model.DeleteDBModel;
import model.GetDBModel;
import model.ModelListener;
import model.SaveModel;
import view.StorageView;

import javax.swing.*;

public class StoragePresenter {
    private StorageView storageView;
    private SaveModel saveModel;
    private GetDBModel dbModel;
    private DeleteDBModel deleteDBModel;
    private String textSaved;

    public StoragePresenter(StorageView storageView, SaveModel saveModel,GetDBModel dbModel,DeleteDBModel deleteDBModel){
        this.storageView = storageView;
        this.saveModel = saveModel;
        this.dbModel = dbModel;
        this.deleteDBModel = deleteDBModel;
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
        storageView.getSavedSeriesTextPane().setContentType("text/html");
        storageView.getSavedSeriesTextPane().setText(textSaved);
    }
    public void onEventClickedDeleteItem(String selectedTitle){
        deleteDBModel.addListener(new ModelListener() {
            @Override
            public void hasFinished() {
                showSavedSeries();
                storageView.getSavedSeriesTextPane().setText("");
            }
        });
        storageView.setWorkingStatus();
        deleteDBModel.deleteItem(selectedTitle);
        storageView.setWatingStatus();
    }
    public void onEventClickedSaveChangesItem(String titleSelect, String textToReplace){
        saveModel.addListener(new ModelListener() {
            @Override
            public void hasFinished() {
                storageView.getSavedSeriesTextPane().setText(textToReplace);
            }
        });
        saveModel.saveLocally(titleSelect,textToReplace);
    }
}
