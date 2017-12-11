package com.example.yjkim.faniddo.domain;

/**
 * Created by yjkim on 2017-12-03.
 */

public class SonBoardVO {

    private int sbno;
    private String sbtitle;
    private String sbcontent;
    private String sbwriter;
    private String sbregdate;

    public int getSbno() {
        return sbno;
    }

    public void setSbno(int sbno) {
        this.sbno = sbno;
    }

    public String getSbtitle() {
        return sbtitle;
    }

    public void setSbtitle(String sbtitle) {
        this.sbtitle = sbtitle;
    }

    public String getSbcontent() {
        return sbcontent;
    }

    public void setSbcontent(String sbcontent) {
        this.sbcontent = sbcontent;
    }

    public String getSbwriter() {
        return sbwriter;
    }

    public void setSbwriter(String sbwriter) {
        this.sbwriter = sbwriter;
    }

    public String getSbregdate() {
        return sbregdate;
    }

    public void setSbregdate(String sbregdate) {
        this.sbregdate = sbregdate;
    }

    @Override
    public String toString() {
        return "SonBoardVO{" +
                "sbno=" + sbno +
                ", sbtitle='" + sbtitle + '\'' +
                ", sbcontent='" + sbcontent + '\'' +
                ", sbwriter='" + sbwriter + '\'' +
                ", sbregdate='" + sbregdate + '\'' +
                '}';
    }
}