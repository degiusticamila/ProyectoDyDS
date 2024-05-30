package view;

import dyds.tvseriesinfo.fulllogic.SearchResult;
import presenter.PagePresenter;
import presenter.SearchPresenter;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SearchView extends JPanel implements View{
    private JTextField seriesToSearchTextField;
    private JButton goSearchButton;
    private JTextPane currentSearchTextPane;
    private JPanel searchPanel;
    private JTextPane savedSeriesTextPane;
    private JPopupMenu searchOptionsMenu;
    private JPopupMenu storedInfoPopup;
    private SearchPresenter searchPresenter;
    private PagePresenter pagePresenter;
    private LinkedList<SearchResult> searchResults;
    public SearchView(){
        initListeners();
        initComponents();
        showView();
    }
    public void showView(){
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
    public JPanel getSearchPanel(){
        return searchPanel;
    }
    public JPopupMenu getPopupMenu(){
        return searchOptionsMenu;
    }
    //se crea en ejecucion
    public void createJPopMenu(){
        searchOptionsMenu = new JPopupMenu("Search Results");

    }
    public void showInfoPopup(){
        searchOptionsMenu.show(seriesToSearchTextField, seriesToSearchTextField.getX(), seriesToSearchTextField.getY());
        initializeSearchResultsPopup();
    }
    private void initListeners(){
        initializeSeriesToSearchTextField();
        initializeGoSearchButton();
        //initializeSearchResultsPopup();
    }
    public void setSearchPresenter(SearchPresenter sp){
        searchPresenter = sp;
    }
    public void setPagePresenter(PagePresenter pp){
        pagePresenter = pp;
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
    public void createSearchResultList(){
        searchResults = new LinkedList<SearchResult>();
    }
    public LinkedList<SearchResult> getSearchResultList(){
        return searchResults;
    }
    private void initializeSeriesToSearchTextField(){
        seriesToSearchTextField.addActionListener(actionEvent -> {
            searchPresenter.onEventClickedGoButtonToSearch();
        });
    }
    private void initializeSearchResultsPopup(){
        for(SearchResult sr : searchResults){
            sr.addActionListener(actionEvent -> {

                //cambiar para que sea clean x el parametro.
                pagePresenter.onEventPopupSelected(sr);
            });
        }
    }
    private void initializeGoSearchButton(){
        goSearchButton.addActionListener(actionEvent ->{
            searchPresenter.onEventClickedGoButtonToSearch();

            //el taskThread deberia declararse aca? pq me falta el action listener del jpopup
        });
    }
}