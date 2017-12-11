package com.example.yjkim.faniddo.domain;

/**
 * Created by yjkim on 2017-11-22.
 */

public class MissionVO {

    private int mno;
    private String mtitle;
    private String mcontent;
    private String mwriter;
    private int mgrade;

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMcontent() {
        return mcontent;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent;
    }

    public String getMwriter() {
        return mwriter;
    }

    public void setMwriter(String mwriter) {
        this.mwriter = mwriter;
    }

    public int getMgrade() {
        return mgrade;
    }

    public void setMgrade(int mgrade) {
        this.mgrade = mgrade;
    }

    @Override
    public String toString() {
        return "MissionVO{" +
                "mno=" + mno +
                ", mtitle='" + mtitle + '\'' +
                ", mcontent='" + mcontent + '\'' +
                ", mwriter='" + mwriter + '\'' +
                ", mgrade=" + mgrade +
                '}';
    }
}
