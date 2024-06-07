package presenter;

import model.SaveModel;
import model.ModelListener;
import view.SearchView;
import view.StorageView;

import javax.swing.*;

public class SavePresenter {
    private SearchView searchView;
    private StorageView storageView;
    private SaveModel saveModel;
    private PagePresenter pagePresenter;
    public SavePresenter(SearchView searchView, StorageView storageView, SaveModel saveModel){
        this.searchView = searchView;
        this.storageView = storageView;
        this.saveModel = saveModel;
    }
    public void onEventSaveLocallyButton(){
        saveModel.addListener(new ModelListener() {
            @Override
            public void hasFinished() {
                showTextInStorageView();
            }
        });
        storageView.setWorkingStatus();
        saveModel.saveLocally(pagePresenter.getLastSelectedResultTitle(), pagePresenter.getLastText());
        storageView.setWatingStatus();
    }
    private void showTextInStorageView(){
        updateSavedSeriesTextPane();
        updateSeriesComboBox();
        showOptionMessage();
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
        storageView.getSeriesComboBox().setModel(new DefaultComboBoxModel(saveModel.getSavedSeries()));
        String title = pagePresenter.getLastSelectedResultTitle();
        storageView.getSeriesComboBox().addItem(title);
    }
    private void showOptionMessage(){
        JOptionPane.showMessageDialog(searchView ,"Series saved locally!", "Save Confirmation", JOptionPane.INFORMATION_MESSAGE);
    }
}
