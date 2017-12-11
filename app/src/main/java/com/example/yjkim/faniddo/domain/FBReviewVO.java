package com.example.yjkim.faniddo.domain;

/**
 * Created by yjkim on 2017-12-01.
 */

public class FBReviewVO {
    private int fbrno;
    private int fbno;
    private String fbrcontent;
    private String fbrwriter;
    private String fbrregdate;

    public int getFbrno() {
        return fbrno;
    }

    public void setFbrno(int fbrno) {
        this.fbrno = fbrno;
    }

    public int getFbno() {
        return fbno;
    }

    public void setFbno(int fbno) {
        this.fbno = fbno;
    }

    public String getFbrcontent() {
        return fbrcontent;
    }

    public void setFbrcontent(String fbrcontent) {
        this.fbrcontent = fbrcontent;
    }

    public String getFbrwriter() {
        return fbrwriter;
    }

    public void setFbrwriter(String fbrwriter) {
        this.fbrwriter = fbrwriter;
    }

    public String getFbrregdate() {
        return fbrregdate;
    }

    public void setFbrregdate(String fbrregdate) {
        this.fbrregdate = fbrregdate;
    }

    @Override
    public String toString() {
        return "FBReviewVO{" +
                "fbrno=" + fbrno +
                ", fbno=" + fbno +
                ", fbrcontent='" + fbrcontent + '\'' +
                ", fbrwriter='" + fbrwriter + '\'' +
                ", fbrregdate='" + fbrregdate + '\'' +
                '}';
    }
}
