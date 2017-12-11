package com.example.yjkim.faniddo.domain;

/**
 * Created by yjkim on 2017-11-29.
 */

public class SFatherVO {
    private int mno;
    private String mid;
    private String mpwd;
    private String mname;
    private String memail;
    private String mprofile;
    private int fano;

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMpwd() {
        return mpwd;
    }

    public void setMpwd(String mpwd) {
        this.mpwd = mpwd;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMemail() {
        return memail;
    }

    public void setMemail(String memail) {
        this.memail = memail;
    }

    public String getMprofile() {
        return mprofile;
    }

    public void setMprofile(String mprofile) {
        this.mprofile = mprofile;
    }

    public int getFano() {
        return fano;
    }

    public void setFano(int fano) {
        this.fano = fano;
    }

    @Override
    public String toString() {
        return "SFatherVO{" +
                "mno=" + mno +
                ", mid='" + mid + '\'' +
                ", mpwd='" + mpwd + '\'' +
                ", mname='" + mname + '\'' +
                ", memail='" + memail + '\'' +
                ", mprofile='" + mprofile + '\'' +
                ", fano=" + fano +
                '}';
    }
}
