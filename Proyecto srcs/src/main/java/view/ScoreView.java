package view;

import model.RankedSeries;
import presenter.ScorePresenter;

import javax.swing.*;
import java.awt.*;

public class ScoreView extends JPanel implements View{

    private JPanel scorePanel;
    private JComboBox<RankedSeries> ratedSeriesComboBox;
    private ScorePresenter scorePresenter;
    public ScoreView(){
        initComponents();
        showView();
    }
    @Override
    public void showView() {
        scorePanel.setVisible(true);
    }
    public void initComponents(){
        createScorePanel();
        createRatedSeriesComboBox();
    }
    private void createScorePanel(){
        scorePanel = new JPanel();
    }
    private void createRatedSeriesComboBox(){
        ratedSeriesComboBox = new JComboBox<>();
        ratedSeriesComboBox.setPreferredSize(new Dimension(400, 30));
        scorePanel.add(ratedSeriesComboBox);
    }
    public JPanel getScorePanel(){
        return scorePanel;
    }
    public JComboBox getRatedSeriesComboBox(){
        return ratedSeriesComboBox;
    }
    public void setScorePresenter(ScorePresenter scorePresenter){
        this.scorePresenter = scorePresenter;
        scorePresenter.updateScoreComboBox();
        initializeRankedComboBoxListeners();
    }
    private void initializeRankedComboBoxListeners(){
        ratedSeriesComboBox.addActionListener(actionEvent -> {
            RankedSeries selectedSeries = (RankedSeries) ratedSeriesComboBox.getSelectedItem();
            if (selectedSeries != null) {
                scorePresenter.onEventComboBoxRankSelected(selectedSeries);
            }
        });
    }
}
