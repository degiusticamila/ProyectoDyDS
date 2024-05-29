package view;

import presenter.SearchPresenter;

import javax.swing.*;
import java.awt.*;

public class SearchView extends JPanel implements View{
    private JTextField seriesToSearchTextField;
    private JButton goSearchButton;
    private JTextPane currentSearchTextPane;
    private JPanel searchPanel;

    private JButton saveLocallyButton;
    private JTabbedPane tabbedPane1;


    private JTextPane savedSeriesTextPane;
    private JPopupMenu searchOptionsMenu;
    private JPopupMenu storedInfoPopup;
    private SearchPresenter searchPresenter;
    public SearchView(){
        initListeners();
        initComponents();
        showView();

    }
    public void showView(){
        //JFrame frame = new JFrame("TV Series Info Repo");
        //Ojo con el content Pane!
        System.out.println(seriesToSearchTextField);

        //frame.setContentPane(contentPane);
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.pack();
        //frame.setVisible(true);
        searchPanel.setVisible(true);
    }
    public void initComponents(){
        searchPanel = new JPanel();
        currentSearchTextPane.setPreferredSize(new Dimension(500, 500));
        searchPanel.add(seriesToSearchTextField);
        searchPanel.add(goSearchButton);
        searchPanel.add(currentSearchTextPane);
    }
    public JTextField getSeriesToSearchTextField(){
        return seriesToSearchTextField;
    }
    public JButton getGoSearchButton(){
        return goSearchButton;
    }
    public JTextPane getCurrentSearchTextPane(){
        return currentSearchTextPane;
    }
    public JButton getSaveLocallyButton(){
        return saveLocallyButton;
    }
    public JTabbedPane getTabbedPane1(){
        return tabbedPane1;
    }
    public JPanel getSearchPanel(){
        return searchPanel;
    }
    public JTextPane getSavedSeriesTextPane(){
        return savedSeriesTextPane;
    }
    public JPopupMenu getPopupMenu(){
        return searchOptionsMenu;
    }
    public JPopupMenu getStoredInfoPopup(){
        return storedInfoPopup;
    }
    //se crea en ejecucion
    public void createStoredInfoPopup(){
        storedInfoPopup = new JPopupMenu();
    }
    public void createJPopMenu(){
        searchOptionsMenu = new JPopupMenu("Search Results");

    }
    private void initListeners(){
        seriesToSearchTextField.addActionListener(actionEvent -> {
            searchPresenter.onEventClickedGoButtonToSearch();
        });
        goSearchButton.addActionListener(actionEvent ->{
            searchPresenter.onEventClickedGoButtonToSearch();

            //el taskThread deberia declararse aca? pq me falta el action listener del jpopup
        });
        initializeItemsPopup();
    }
    public void setSearchPresenter(SearchPresenter sp){
        searchPresenter = sp;
    }
    public void createSearchResult(){

    }
    public void setWorkingStatus() {
        for (Component c : searchPanel.getComponents()) {
            c.setEnabled(false);
        }
        currentSearchTextPane.setEnabled(false);
    }
    public void setWatingStatus () {
        for (Component c : searchPanel.getComponents()) c.setEnabled(true);
        currentSearchTextPane.setEnabled(true);
    }
    private void initializePopupItems(){
//        for(SearchResult sr : searchOptionsMenu){
//            sr.add
////        }
    }
}

