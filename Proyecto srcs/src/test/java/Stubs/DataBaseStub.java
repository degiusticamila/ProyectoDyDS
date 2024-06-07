package Stubs;

import model.DataBaseInterface;

import java.sql.Date;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

public class DataBaseStub implements DataBaseInterface {
    @Override
    public void loadDatabase() {
        System.out.println("loadDatabase");
    }

    @Override
    public ArrayList<String> getTitles() {
        System.out.println("getTitles");
        ArrayList<String> titles = new ArrayList<>();
        titles.add("title");
        return titles;
    }

    @Override
    public void saveInfo(String title, String extract) {
        System.out.println("saveInfo");
    }

    @Override
    public String getExtract(String title) {
        System.out.println("getExtract");
        return "EXAMPLE EXTRACT";
    }

    @Override
    public void deleteEntry(String title) {
        System.out.println("deleteEntry");
    }

    @Override
    public void saveScore(String title, Integer score) {
        System.out.println("saveScore");
    }

    @Override
    public String getScores(String title) {
        System.out.println("getScores");
        return "-1";
    }

    @Override
    public Date getDates(String title) {
        System.out.println("getDates");
        String dateStr = "2003-03-17";
        Date date = Date.valueOf(dateStr);
        return date;
    }

    @Override
    public ArrayList<String> getTitlesScores() {
        System.out.println("getTitlesScores");
        ArrayList<String> titlesScores = new ArrayList<>();
        titlesScores.add("title1: 2");
        titlesScores.add("title2: 5");
        titlesScores.add("title3: 9");
        return titlesScores;
    }
}
