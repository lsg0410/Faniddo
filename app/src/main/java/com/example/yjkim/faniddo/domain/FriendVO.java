package com.example.yjkim.faniddo.domain;

/**
 * Created by yjkim on 2017-11-29.
 */

public class FriendVO {

    private int mno;
    private int fno;

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public int getFno() {
        return fno;
    }

    public void setFno(int fno) {
        this.fno = fno;
    }

    @Override
    public String toString() {
        return "FriendVO{" +
                "mno=" + mno +
                ", fno=" + fno +
                '}';
    }
}
