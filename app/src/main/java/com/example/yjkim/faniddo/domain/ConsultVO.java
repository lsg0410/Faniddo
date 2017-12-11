package com.example.yjkim.faniddo.domain;

/**
 * Created by yjkim on 2017-11-23.
 */

public class ConsultVO {

    private int cno;
    private String ctitle;
    private String ccontent;
    private String cwriter;
    private String cregdate;

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    public String getCtitle() {
        return ctitle;
    }

    public void setCtitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public String getCcontent() {
        return ccontent;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public String getCwriter() {
        return cwriter;
    }

    public void setCwriter(String cwriter) {
        this.cwriter = cwriter;
    }

    public String getCregdate() {
        return cregdate;
    }

    public void setCregdate(String cregdate) {
        this.cregdate = cregdate;
    }

    @Override
    public String toString() {
        return "ConsultVO{" +
                "cno=" + cno +
                ", ctitle='" + ctitle + '\'' +
                ", ccontent='" + ccontent + '\'' +
                ", cwriter='" + cwriter + '\'' +
                ", cregdate='" + cregdate + '\'' +
                '}';
    }
}
