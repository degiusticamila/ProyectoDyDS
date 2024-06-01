package presenter;

import model.DataBaseModel;
import model.ModelListener;
import view.StorageView;

import javax.swing.*;

public class StoragePresenter {
    private StorageView storageView;
    private DataBaseModel dataBaseModel;
    private Object[] savedSeries;

    public StoragePresenter(StorageView storageView, DataBaseModel dataBaseModel){
        this.storageView = storageView;
        this.dataBaseModel = dataBaseModel;
    }
    public void onEventClickedSeriesComboBox(){
        dataBaseModel.addListener(new ModelListener() {
            @Override
            public void hasFinished() {
                showSavedSeries();
            }
        });
        storageView.setWorkingStatus();
        savedSeries = dataBaseModel.getSavedSeries();
        storageView.setWatingStatus();
    }
    private void showSavedSeries(){
        storageView.getSeriesComboBox().setModel(new DefaultComboBoxModel(savedSeries));
    }
}
