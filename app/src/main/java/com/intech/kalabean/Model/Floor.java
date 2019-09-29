package com.intech.kalabean.Model;

public class Floor {
    private int id;
    private String TitleFA;
    private int OrderNo;
    private int Refid;
    private long SentDate;
    private boolean Status;
    private String TitleEN;
    private String TitleAR;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleFA() {
        return TitleFA;
    }

    public void setTitleFA(String titleFA) {
        TitleFA = titleFA;
    }

    public int getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(int orderNo) {
        OrderNo = orderNo;
    }

    public int getRefid() {
        return Refid;
    }

    public void setRefid(int refid) {
        Refid = refid;
    }

    public long getSentDate() {
        return SentDate;
    }

    public void setSentDate(long sentDate) {
        SentDate = sentDate;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getTitleEN() {
        return TitleEN;
    }

    public void setTitleEN(String titleEN) {
        TitleEN = titleEN;
    }

    public String getTitleAR() {
        return TitleAR;
    }

    public void setTitleAR(String titleAR) {
        TitleAR = titleAR;
    }
}
