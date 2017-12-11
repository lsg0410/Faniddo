package com.example.yjkim.faniddo.domain;

/**
 * Created by yjkim on 2017-11-29.
 */

public class SFriendVO {
    private int mno;
    private String mid;
    private String mpwd;
    private String mname;
    private String memail;
    private String mprofile;
    private int fno;

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

    public int getFno() {
        return fno;
    }

    public void setFno(int fno) {
        this.fno = fno;
    }

    @Override
    public String toString() {
        return "SFriendVO{" +
                "mno=" + mno +
                ", mid='" + mid + '\'' +
                ", mpwd='" + mpwd + '\'' +
                ", mname='" + mname + '\'' +
                ", memail='" + memail + '\'' +
                ", mprofile='" + mprofile + '\'' +
                ", fno=" + fno +
                '}';
    }
}
