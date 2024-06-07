package model;

import java.sql.Date;
import java.util.ArrayList;

public interface DataBaseInterface {
    public void loadDatabase();
    public ArrayList<String> getTitles();
    public void saveInfo(String title, String extract);
    public String getExtract(String title);
    public void deleteEntry(String title);
    public void saveScore(String title, Integer score);
    public String getScores(String title);
    public Date getDates(String title);
    public ArrayList<String> getTitlesScores();
}
