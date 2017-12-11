package com.example.yjkim.faniddo.domain;

/**
 * Created by yjkim on 2017-11-14.
 */

public class BoardVO {

    private int bno;
    private String btitle;
    private String bcontent;
    private String bwriter;
    private String bregdate;
    private String bfilename;

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public String getBwriter() {
        return bwriter;
    }

    public void setBwriter(String bwriter) {
        this.bwriter = bwriter;
    }

    public String getBregdate() {
        return bregdate;
    }

    public void setBregdate(String bregdate) {
        this.bregdate = bregdate;
    }

    public String getBfilename() {
        return bfilename;
    }

    public void setBfilename(String bfilename) {
        this.bfilename = bfilename;
    }

    @Override
    public String toString() {
        return "BoardVO{" +
                "bno=" + bno +
                ", btitle='" + btitle + '\'' +
                ", bcontent='" + bcontent + '\'' +
                ", bwriter='" + bwriter + '\'' +
                ", bregdate='" + bregdate + '\'' +
                ", bfilename='" + bfilename + '\'' +
                '}';
    }
}
