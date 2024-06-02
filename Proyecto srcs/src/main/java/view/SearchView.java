package view;

import dyds.tvseriesinfo.fulllogic.SearchResult;
import presenter.PagePresenter;
import presenter.SavePresenter;
import presenter.SearchPresenter;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class SearchView extends JPanel implements View{
    private SearchResult searchResultActual;
    private JTextField seriesToSearchTextField;
    private JButton goSearchButton;
    private JTextPane currentSearchTextPane;
    private JPanel searchPanel;
    private JPopupMenu searchOptionsMenu;
    private SearchPresenter searchPresenter;
    private PagePresenter pagePresenter;
    private SavePresenter savePresenter;
    private JButton saveLocallyButton;
    private JScrollPane scrollPaneCurrentSearch;
    private LinkedList<SearchResult> searchResults;
    public SearchView(){

        initComponents();
        showView();
        initListeners();
    }
    public void showView(){
        searchPanel.setVisible(true);
    }
    public void initComponents(){
        searchPanel = new JPanel();
        //createSaveLocallyButton();
        //currentSearchTextPane.setPreferredSize(new Dimension(500, 500));
        searchPanel.add(seriesToSearchTextField);
        searchPanel.add(goSearchButton);
        //searchPanel.add(currentSearchTextPane);
        searchPanel.add(saveLocallyButton);
        createCurrentSearchPane();
        createScrollPane();
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
        initializeSaveLocallyButton();

    }
    public void setSearchPresenter(SearchPresenter searchPresenter){
        this.searchPresenter = searchPresenter;
    }
    public void setPagePresenter(PagePresenter pagePresenter){
        this.pagePresenter = pagePresenter;
    }
    public void setSavePresenter(SavePresenter savePresenter){
        this.savePresenter = savePresenter;
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
                searchResultActual = sr;
                //cambiar para que sea clean x el parametro.
                pagePresenter.onEventPopupSelected(sr);
            });
        }
    }
    private void initializeGoSearchButton(){
        goSearchButton.addActionListener(actionEvent ->{
            searchPresenter.onEventClickedGoButtonToSearch();
        });
    }
    private void initializeSaveLocallyButton(){
        saveLocallyButton.addActionListener(actionEvent ->{
            savePresenter.onEventSaveLocallyButton();
        });
    }
    private void createCurrentSearchPane(){
        currentSearchTextPane = new JTextPane();
        currentSearchTextPane.setPreferredSize(new Dimension(500, 500));
    }
    private void createScrollPane(){
        scrollPaneCurrentSearch = new JScrollPane(currentSearchTextPane);
        searchPanel.add(scrollPaneCurrentSearch,BorderLayout.CENTER);
    }
    //No se como cambiarlo para que sea clean.
    public SearchResult getSearchResultActual(){
        return searchResultActual;
    }
}