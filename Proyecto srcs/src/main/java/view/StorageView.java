package view;

import model.SaveModel;
import presenter.StoragePresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StorageView extends JPanel implements View {
    private StoragePresenter storagePresenter;
    private SaveModel dataBaseModel;
    private JPopupMenu storedInfoPopup;
    private JTextPane savedSeriesTextPane;
    private JPanel storagePanel;
    private JScrollPane savedSeriesScrollPane;
    private JMenuItem deleteItem;
    private JComboBox seriesComboBox;
    private JMenuItem saveChanges;
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
        createSavedSeriesScrollPane();
        createStoredInfoPopup();
        createDeleteItem();
        createSaveChangesItem();
    }
    public void initListeners(){
        initializeSeriesComboBox();
        initializeDeleteItem();
        initializeSaveChangesItem();
    }
    private void initializeSeriesComboBox(){
        seriesComboBox.addActionListener(actionEvent ->{
            String selectedTitle = (String) seriesComboBox.getSelectedItem();
            storagePresenter.onEventClickedSeriesComboBox(selectedTitle);
        });
    }
    private void initializeDeleteItem(){
        deleteItem.addActionListener(actionEvent ->{
            String titleSelectToDelete = (String) seriesComboBox.getSelectedItem();
            System.out.println("titulo seleccionado para borrar: "+titleSelectToDelete);
            if(seriesComboBox.getSelectedIndex()  > -1){
                storagePresenter.onEventClickedDeleteItem(titleSelectToDelete);
            }
        });
    }
    private void initializeSaveChangesItem(){
        saveChanges.addActionListener(actionEvent ->{
            String titleSelect = (String) seriesComboBox.getSelectedItem();
            String textToReplace = savedSeriesTextPane.getText();
            storagePresenter.onEventClickedSaveChangesItem(titleSelect,textToReplace);
        });
    }
    private void initializePopupMenu(){
        savedSeriesTextPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
        });
    }
    private void showPopup(MouseEvent e){
        if (e.isPopupTrigger() && storedInfoPopup != null) {
            storedInfoPopup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    public JPanel getStoragePanel(){
        return storagePanel;
    }
    public JTextPane getSavedSeriesTextPane(){
        return savedSeriesTextPane;
    }
    public JComboBox getSeriesComboBox(){
        return seriesComboBox;
    }
   private void createStoragePanel(){
        storagePanel = new JPanel();
       setSize(800, 600);
    }
    private void createSavedSeriesTextPane(){
        savedSeriesTextPane = new JTextPane();
        savedSeriesTextPane.setPreferredSize(new Dimension(500, 500));
        //savedSeriesTextPane.setMinimumSize(new Dimension(500, 500));
        initializePopupMenu();
        storagePanel.add(savedSeriesTextPane);
    }
    private void createSeriesComboBox(){
        seriesComboBox = new JComboBox();
        seriesComboBox.setPreferredSize(new Dimension(400, 30));  // Agrandar el JComboBox
        seriesComboBox.setMinimumSize(new Dimension(400, 30));
        storagePanel.add(seriesComboBox);
    }
    private void createSavedSeriesScrollPane(){
        savedSeriesScrollPane = new JScrollPane(savedSeriesTextPane);
        storagePanel.add(savedSeriesScrollPane,BorderLayout.CENTER);
    }
    public void setWorkingStatus() {
        for (Component c : storagePanel.getComponents()) {
            c.setEnabled(false);
        }
        savedSeriesTextPane.setEnabled(false);
        seriesComboBox.setEnabled(false);
    }
    public void setWatingStatus () {
        for (Component c : storagePanel.getComponents()) c.setEnabled(true);
        savedSeriesTextPane.setEnabled(true);
        seriesComboBox.setEnabled(true);
    }
    public void setStoragePresenter(StoragePresenter storagePresenter){
        this.storagePresenter = storagePresenter;
        updateStorageComboBox();
    }
    private void createStoredInfoPopup(){
        storedInfoPopup = new JPopupMenu();
        storagePanel.setComponentPopupMenu(storedInfoPopup);
    }
    private void createDeleteItem(){
        deleteItem = new JMenuItem("Delete!");
        storedInfoPopup.add(deleteItem);
    }
    private void createSaveChangesItem(){
        saveChanges = new JMenuItem("Save changes!");
        storedInfoPopup.add(saveChanges);
    }
    private void updateStorageComboBox(){
        storagePresenter.showSavedSeries();
    }
}
