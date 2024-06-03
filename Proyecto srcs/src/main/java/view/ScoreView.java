package view;

import javax.swing.*;
import java.awt.*;

public class ScoreView extends JPanel implements View{

    private JPanel scorePanel;
    //private ScoreItem scoreItem;
    private JButton modifyScore;
    private JComboBox ratedSeriesComboBox;

    public ScoreView(){
        initComponents();
       //initListeners();
        showView();
    }
    @Override
    public void showView() {
        scorePanel.setVisible(true);
    }
    public void initComponents(){
        createScorePanel();
        createRatedSeriesComboBox();
        createModifyScoreButton();
    }
    public void initListeners(){
        //acá si se elige una serie calificada se tiene que poder modificar el puntaje
    }
    private void createScorePanel(){
        scorePanel = new JPanel();
    }
    private void createRatedSeriesComboBox(){
        //se tiene que crear una sola vez y dsp crearse con las cosas que tenia antes.
        ratedSeriesComboBox = new JComboBox<>();
        ratedSeriesComboBox.setPreferredSize(new Dimension(400, 30));
        scorePanel.add(ratedSeriesComboBox);
    }
    private void createModifyScoreButton(){
        modifyScore = new JButton();
    }
    public JPanel getScorePanel(){
        return scorePanel;
    }
    public JComboBox getRatedSeriesComboBox(){
        return ratedSeriesComboBox;
    }
}
