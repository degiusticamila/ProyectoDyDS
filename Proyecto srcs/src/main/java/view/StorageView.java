package view;

import model.DataBaseModel;
import presenter.StoragePresenter;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;

public class StorageView extends JPanel implements View {
    private StoragePresenter storagePresenter;
    private DataBaseModel dataBaseModel;
    private JTextPane savedSeriesTextPane;
    private JPanel storagePanel;
    private JScrollPane savedSeriesScrollPane;
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
        createSavedSeriesScrollPane();

    }
    public void initListeners(){
        initializeSeriesComboBox();
    }

    //Aca hay que actualizar el textPane
    private void initializeSeriesComboBox(){
        System.out.println(storagePresenter);

        seriesComboBox.addActionListener(actionEvent ->{
            System.out.println("se elige una opcion del combo box, falta agregar dicha funcionalidad");

        });

        /**

        seriesComboBox.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                System.out.println(storagePresenter);
                storagePresenter.onEventClickedSeriesComboBox();
                System.out.println("se desplega el Combo Box de series guardadas :)");
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });
        **/
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
        savedSeriesTextPane.setPreferredSize(new Dimension(500, 300));
        savedSeriesTextPane.setMinimumSize(new Dimension(500, 300));
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
    //no se si respeta demasiado mvp :(
    private void updateStorageComboBox(){
        storagePresenter.showSavedSeries();
    }
}
