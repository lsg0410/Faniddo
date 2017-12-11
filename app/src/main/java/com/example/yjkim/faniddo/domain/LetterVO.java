package com.example.yjkim.faniddo.domain;

/**
 * Created by yjkim on 2017-11-14.
 */

public class LetterVO {

    private int lno;
    private String ltitle;
    private String lcontent;
    private String lwriter;
    private String lsender;
    private String lregdate;

    public int getLno() {
        return lno;
    }

    public void setLno(int lno) {
        this.lno = lno;
    }

    public String getLtitle() {
        return ltitle;
    }

    public void setLtitle(String ltitle) {
        this.ltitle = ltitle;
    }

    public String getLcontent() {
        return lcontent;
    }

    public void setLcontent(String lcontent) {
        this.lcontent = lcontent;
    }

    public String getLwriter() {
        return lwriter;
    }

    public void setLwriter(String lwriter) {
        this.lwriter = lwriter;
    }

    public String getLsender() {
        return lsender;
    }

    public void setLsender(String lsender) {
        this.lsender = lsender;
    }

    public String getLregdate() {
        return lregdate;
    }

    public void setLregdate(String lregdate) {
        this.lregdate = lregdate;
    }

    @Override
    public String toString() {
        return "LetterVO{" +
                "lno=" + lno +
                ", ltitle='" + ltitle + '\'' +
                ", lcontent='" + lcontent + '\'' +
                ", lwriter='" + lwriter + '\'' +
                ", lsender='" + lsender + '\'' +
                ", lregdate='" + lregdate + '\'' +
                '}';
    }
}
