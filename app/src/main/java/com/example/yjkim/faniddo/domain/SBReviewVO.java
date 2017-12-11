package com.example.yjkim.faniddo.domain;

/**
 * Created by yjkim on 2017-12-04.
 */

public class SBReviewVO {
    private int sbrno;
    private int sbno;
    private String sbrcontent;
    private String sbrwriter;
    private String sbrregdate;

    public int getSbrno() {
        return sbrno;
    }

    public void setSbrno(int sbrno) {
        this.sbrno = sbrno;
    }

    public int getSbno() {
        return sbno;
    }

    public void setSbno(int sbno) {
        this.sbno = sbno;
    }

    public String getSbrcontent() {
        return sbrcontent;
    }

    public void setSbrcontent(String sbrcontent) {
        this.sbrcontent = sbrcontent;
    }

    public String getSbrwriter() {
        return sbrwriter;
    }

    public void setSbrwriter(String sbrwriter) {
        this.sbrwriter = sbrwriter;
    }

    public String getSbrregdate() {
        return sbrregdate;
    }

    public void setSbrregdate(String sbrregdate) {
        this.sbrregdate = sbrregdate;
    }

    @Override
    public String toString() {
        return "SBReviewVO{" +
                "sbrno=" + sbrno +
                ", sbno=" + sbno +
                ", sbrcontent='" + sbrcontent + '\'' +
                ", sbrwriter='" + sbrwriter + '\'' +
                ", sbrregdate='" + sbrregdate + '\'' +
                '}';
    }
}
