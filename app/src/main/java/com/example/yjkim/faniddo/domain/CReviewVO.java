package com.example.yjkim.faniddo.domain;

/**
 * Created by yjkim on 2017-11-23.
 */

public class CReviewVO {

    private int crno;
    private int cno;
    private String crcontent;
    private String crwriter;
    private String crregdate;

    public int getCrno() {
        return crno;
    }

    public void setCrno(int crno) {
        this.crno = crno;
    }

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public String getCrcontent() {
        return crcontent;
    }

    public void setCrcontent(String crcontent) {
        this.crcontent = crcontent;
    }

    public String getCrwriter() {
        return crwriter;
    }

    public void setCrwriter(String crwriter) {
        this.crwriter = crwriter;
    }

    public String getCrregdate() {
        return crregdate;
    }

    public void setCrregdate(String crregdate) {
        this.crregdate = crregdate;
    }

    @Override
    public String toString() {
        return "CReviewVO{" +
                "crno=" + crno +
                ", cno=" + cno +
                ", crcontent='" + crcontent + '\'' +
                ", crwriter='" + crwriter + '\'' +
                ", crregdate='" + crregdate + '\'' +
                '}';
    }
}
