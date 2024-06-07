package view;

import utils.SearchResult;
import presenter.PagePresenter;
import presenter.SavePresenter;
import presenter.ScorePresenter;
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
    private ScorePresenter scorePresenter;
    private JButton saveLocallyButton;
    private JScrollPane scrollPaneCurrentSearch;
    private LinkedList<SearchResult> searchResults;
    private JButton scoreButton;
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
        searchPanel.add(seriesToSearchTextField);
        searchPanel.add(goSearchButton);
        searchPanel.add(saveLocallyButton);
        createCurrentSearchPane();
        createScrollPane();
        searchPanel.add(currentSearchTextPane);
        createGiveScoreButton();
    }
    public JTextField getSeriesToSearchTextField(){
        return seriesToSearchTextField;
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
        initializeScoreButton();
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
    public void setScorePresenter(ScorePresenter scorePresenter){
        this.scorePresenter = scorePresenter;
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
    private void initializeScoreButton(){
        scoreButton.addActionListener(actionEvent -> {
            String input = JOptionPane.showInputDialog(searchPanel, "Enter a score");
            try {
                Integer score = Integer.parseInt(input);
                if (score < 1 || score > 10)
                    JOptionPane.showMessageDialog(searchPanel, "The score must be between 1 and 10.", "Invalid Score", JOptionPane.WARNING_MESSAGE);
                else
                    scorePresenter.onEventClickedScoreButton(score, searchResultActual);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(searchPanel, "Por favor, ingrese un número válido.", "Entrada inválida", JOptionPane.ERROR_MESSAGE);
            }
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
    private void createGiveScoreButton(){
        scoreButton = new JButton("Define score");
        searchPanel.add(scoreButton);
    }
    public void addSearchOptionsMenu(SearchResult sr){
        searchOptionsMenu.add(sr);
    }
    public void notifyUnexpectedEvent(String message){
        JOptionPane.showMessageDialog(this,message);
    }
}