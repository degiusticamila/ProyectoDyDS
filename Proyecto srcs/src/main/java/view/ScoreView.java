package view;

import model.RankedSeries;
import presenter.ScorePresenter;

import javax.swing.*;
import java.awt.*;

public class ScoreView extends JPanel implements View{

    private JPanel scorePanel;
    private JList<RankedSeries> rankedSeriesJList;
    private JComboBox<RankedSeries> ratedSeriesComboBox;
    private ScorePresenter scorePresenter;
    private JScrollPane scrollPaneRankedList;

    public ScoreView(){
        initComponents();
       initListeners();
        showView();
    }
    @Override
    public void showView() {
        scorePanel.setVisible(true);
    }
    public void initComponents(){
        createScorePanel();
        createRatedSeriesComboBox();
        //createRatedSeriesJList();
    }
    public void initListeners(){
    }
    private void createScorePanel(){
        scorePanel = new JPanel();
    }
    private void createRatedSeriesComboBox(){
        ratedSeriesComboBox = new JComboBox<RankedSeries>();
        ratedSeriesComboBox.setPreferredSize(new Dimension(400, 30));
        scorePanel.add(ratedSeriesComboBox);
    }
    private void createRatedSeriesJList(){
        rankedSeriesJList = new JList<>();
        rankedSeriesJList.setPreferredSize(new Dimension(400, 30));
        scorePanel.add(rankedSeriesJList);
        setRankedSeriesModel();
    }
    private void setRankedSeriesModel(){
        DefaultListModel<RankedSeries> listModel = new DefaultListModel<>();
        rankedSeriesJList.setModel(listModel);
        scrollPaneRankedList = new JScrollPane(rankedSeriesJList);
        scorePanel.add(scrollPaneRankedList);
    }
    public JPanel getScorePanel(){
        return scorePanel;
    }
    public JComboBox getRatedSeriesComboBox(){
        return ratedSeriesComboBox;
    }
    public JList getRatedSeriesList(){
        return rankedSeriesJList;
    }
    public void setScorePresenter(ScorePresenter scorePresenter){
        this.scorePresenter = scorePresenter;

        scorePresenter.updateScoreComboBox();
        //initializeRankedItemsComboBox();
        ratedSeriesComboBox.addActionListener(actionEvent -> {
            RankedSeries selectedSeries = (RankedSeries) ratedSeriesComboBox.getSelectedItem();
            if (selectedSeries != null) {
                scorePresenter.onEventComboBoxRankSelected(selectedSeries);
            }
        });
    }
    public void initializeRankedItemsComboBox(){
        for(RankedSeries rankedSeries : scorePresenter.getRatedSeries()){
            System.out.println("for del listener");
            rankedSeries.addActionListener(actionEvent ->{
                scorePresenter.onEventComboBoxRankSelected(rankedSeries);
            });
        }
    }
}
