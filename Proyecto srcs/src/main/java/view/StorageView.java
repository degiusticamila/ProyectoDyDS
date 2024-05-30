package view;

import model.DataBaseModel;
import presenter.StoragePresenter;

import javax.swing.*;

public class StorageView extends JPanel implements View {
    private StoragePresenter storagePresenter;
    private DataBaseModel dataBaseModel;
    private JTextPane savedSeriesTextPane;
    private JPanel storagePanel;
    private JComboBox seriesComboBox;
    public StorageView(){
        initComponents();
        initListeners();
        showView();
    }

    public void showView() {
        storagePanel.setVisible(true);
    }
    public void initComponents(){
        createStoragePanel();
        createSavedSeriesTextPane();
        createSeriesComboBox();
        //storagePanel.add(seriesComboBox);
        //storagePanel.add(savedSeriesTextPane);


    }
    public void initListeners(){

    }
    public JPanel getStoragePanel(){
        return storagePanel;
    }
   private void createStoragePanel(){
        storagePanel = new JPanel();
    }
    private void createSavedSeriesTextPane(){
        savedSeriesTextPane = new JTextPane();
        storagePanel.add(savedSeriesTextPane);
    }
    private void createSeriesComboBox(){
        seriesComboBox = new JComboBox();
        storagePanel.add(seriesComboBox);
    }
}
