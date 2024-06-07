package utils;

import javax.swing.*;

public class SearchResult extends JMenuItem {
    public String title;
    public String pageID;
    public String snippet;
    public ImageIcon scoreIcon;
    private Integer scoreValue;
    public SearchResult(String title, String pageID, String snippet) {
        String itemText = "<html><font face=\"arial\">" + title + ": " + snippet;
        itemText =itemText.replace("<span class=\"searchmatch\">", "")
                .replace("</span>", "");
        this.setText(itemText);
        this.title = title;
        this.pageID = pageID;
        this.snippet = snippet;
        scoreValue = -1;
        scoreIcon = new ImageIcon();
    }
    public void setScoreValue(Integer scoreValue){
        this.scoreValue = scoreValue;
    }
    public void setImageIcon(ImageIcon imageIcon){
        this.scoreIcon = imageIcon;
    }
    public Boolean isRated(){
        if(scoreValue == -1){
            return false;
        }
        return true;
    }
}
