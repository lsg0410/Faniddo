package com.example.yjkim.faniddo.domain;

/**
 * Created by yjkim on 2017-11-29.
 */

public class FatherVO {
    private int mno;
    private int fano;

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public int getFano() {
        return fano;
    }

    public void setFano(int fano) {
        this.fano = fano;
    }

    @Override
    public String toString() {
        return "FatherVO{" +
                "mno=" + mno +
                ", fano=" + fano +
                '}';
    }
}
