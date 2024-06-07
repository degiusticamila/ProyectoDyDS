package Stubs;

import model.DataBaseInterface;

import java.sql.Date;
import java.util.ArrayList;

public class DataBaseStub implements DataBaseInterface {
    @Override
    public void loadDatabase() {

    }

    @Override
    public ArrayList<String> getTitles() {
        return null;
    }

    @Override
    public void saveInfo(String title, String extract) {

    }

    @Override
    public String getExtract(String title) {
        return null;
    }

    @Override
    public void deleteEntry(String title) {

    }

    @Override
    public void saveScore(String title, Integer score) {

    }

    @Override
    public String getScores(String title) {
        return null;
    }

    @Override
    public Date getDates(String title) {
        return null;
    }

    @Override
    public ArrayList<String> getTitlesScores() {
        return null;
    }
}
