package com.example.yjkim.faniddo.domain;

/**
 * Created by yjkim on 2017-11-29.
 */

public class FatherBoardVO {
    private int fbno;
    private String fbtitle;
    private String fbcontent;
    private String fbwriter;
    private String fbregdate;

    public int getFbno() {
        return fbno;
    }

    public void setFbno(int fbno) {
        this.fbno = fbno;
    }

    public String getFbtitle() {
        return fbtitle;
    }

    public void setFbtitle(String fbtitle) {
        this.fbtitle = fbtitle;
    }

    public String getFbcontent() {
        return fbcontent;
    }

    public void setFbcontent(String fbcontent) {
        this.fbcontent = fbcontent;
    }

    public String getFbwriter() {
        return fbwriter;
    }

    public void setFbwriter(String fbwriter) {
        this.fbwriter = fbwriter;
    }

    public String getFbregdate() {
        return fbregdate;
    }

    public void setFbregdate(String fbregdate) {
        this.fbregdate = fbregdate;
    }

    @Override
    public String toString() {
        return "FatherBoardVO{" +
                "fbno=" + fbno +
                ", fbtitle='" + fbtitle + '\'' +
                ", fbcontent='" + fbcontent + '\'' +
                ", fbwriter='" + fbwriter + '\'' +
                ", fbregdate='" + fbregdate + '\'' +
                '}';
    }
}
