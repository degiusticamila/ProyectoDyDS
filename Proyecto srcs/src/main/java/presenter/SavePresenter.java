package presenter;

import model.DataBase;
import model.DataBaseModel;
import model.ModelListener;
import view.SearchView;
import view.StorageView;

import javax.swing.*;

public class SavePresenter {
    private SearchView searchView;
    private StorageView storageView;
    private DataBaseModel dataBaseModel;
    private PagePresenter pagePresenter;

    public SavePresenter(SearchView searchView,StorageView storageView,DataBaseModel dataBaseModel){
        this.searchView = searchView;
        this.storageView = storageView;
        this.dataBaseModel = dataBaseModel;
    }
    public void onEventSaveLocallyButton(){
        dataBaseModel.addListener(new ModelListener() {
            @Override
            public void hasFinished() {
                showTextInStorageView();
            }
        });
        storageView.setWorkingStatus();
        dataBaseModel.saveLocally(pagePresenter.getLastSelectedResultTitle(), pagePresenter.getLastText());
        storageView.setWatingStatus();
    }
    private void showTextInStorageView(){
        updateSavedSeriesTextPane();
        updateSeriesComboBox();

    }
    public void setPagePresenter(PagePresenter pagePresenter){
        this.pagePresenter = pagePresenter;
    }
    private void updateSavedSeriesTextPane(){
        storageView.getSavedSeriesTextPane().setContentType("text/html");
        String text = searchView.getCurrentSearchTextPane().getText();

        storageView.getSavedSeriesTextPane().setText(text);
        storageView.getSavedSeriesTextPane().setCaretPosition(0);
    }
    private void updateSeriesComboBox(){
        //mejorar lo de pedir al modelo las series.
        storageView.getSeriesComboBox().setModel(new DefaultComboBoxModel(dataBaseModel.getSavedSeries()));
        String title = pagePresenter.getLastSelectedResultTitle();
        storageView.getSeriesComboBox().addItem(title);
    }
}
