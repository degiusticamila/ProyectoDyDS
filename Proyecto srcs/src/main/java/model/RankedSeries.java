package model;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RankedSeries extends JMenuItem {
    private String seriesTitle;
    private Integer score;
    private Date lastModificationDate;

    public RankedSeries(String seriesTitle, Integer score){
        this.seriesTitle = seriesTitle;
        setScore(score);
        this.lastModificationDate = new Date();
    }
    public void setScore(Integer score){
        if (score < 1 || score > 10) {
            throw new IllegalArgumentException("Score must be between 1 and 10");
        }
        this.score = score;
    }
    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }
    public String getLastModificationDateFormatted() {
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd");
        return formateador.format(this.lastModificationDate);
    }
    public Integer getScore(){
        return score;
    }
    public String getSeriesTitle(){
        return seriesTitle;
    }
    @Override
    public String toString() {
        return seriesTitle+" "+score+" "+getLastModificationDateFormatted();
    }

}
