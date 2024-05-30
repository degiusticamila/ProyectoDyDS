package view;

import javax.swing.*;

public class ScoreView extends JPanel implements View{

    private JPanel scorePanel;

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
    }
    public void initListeners(){

    }
    public void createScorePanel(){
        scorePanel = new JPanel();
    }
    public JPanel getScorePanel(){
        return scorePanel;
    }
}
