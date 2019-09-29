package com.intek.kalabean.Model;

import java.util.List;

public class ShopCenter {
    private int id;
    private String TitleFA;
    private String DescriptionFA;
    private String AddressFA;
    private int Cityid;
    private int SellCenterCatID;
    private boolean HasHaraj;
    private long SentDate;
    private boolean Status;
    private int CreatorId;
    private String Point;
    private String TitleEN;
    private String DescriptionEN;
    private String AddressEN;
    private String TitleAR;
    private String DescriptionAR;
    private String AddressAR;
    private String toor;
    private long Center_LastUpdateDate;
    private List<SubSettings> settings;

    public String getTitleFA() {
        return TitleFA;
    }

    public void setTitleFA(String titleFA) {
        TitleFA = titleFA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescriptionFA() {
        return DescriptionFA;
    }

    public void setDescriptionFA(String descriptionFA) {
        DescriptionFA = descriptionFA;
    }

    public String getAddressFA() {
        return AddressFA;
    }

    public void setAddressFA(String addressFA) {
        AddressFA = addressFA;
    }

    public int getCityid() {
        return Cityid;
    }

    public void setCityid(int cityid) {
        Cityid = cityid;
    }

    public int getSellCenterCatID() {
        return SellCenterCatID;
    }

    public void setSellCenterCatID(int sellCenterCatID) {
        SellCenterCatID = sellCenterCatID;
    }

    public boolean isHasHaraj() {
        return HasHaraj;
    }

    public void setHasHaraj(boolean hasHaraj) {
        HasHaraj = hasHaraj;
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

    public int getCreatorId() {
        return CreatorId;
    }

    public void setCreatorId(int creatorId) {
        CreatorId = creatorId;
    }

    public String getPoint() {
        return Point;
    }

    public void setPoint(String point) {
        Point = point;
    }

    public String getTitleEN() {
        return TitleEN;
    }

    public void setTitleEN(String titleEN) {
        TitleEN = titleEN;
    }

    public String getDescriptionEN() {
        return DescriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        DescriptionEN = descriptionEN;
    }

    public String getAddressEN() {
        return AddressEN;
    }

    public void setAddressEN(String addressEN) {
        AddressEN = addressEN;
    }

    public String getTitleAR() {
        return TitleAR;
    }

    public void setTitleAR(String titleAR) {
        TitleAR = titleAR;
    }

    public String getDescriptionAR() {
        return DescriptionAR;
    }

    public void setDescriptionAR(String descriptionAR) {
        DescriptionAR = descriptionAR;
    }

    public String getAddressAR() {
        return AddressAR;
    }

    public void setAddressAR(String addressAR) {
        AddressAR = addressAR;
    }

    public String getToor() {
        return toor;
    }

    public void setToor(String toor) {
        this.toor = toor;
    }

    public long getCenter_LastUpdateDate() {
        return Center_LastUpdateDate;
    }

    public void setCenter_LastUpdateDate(long center_LastUpdateDate) {
        Center_LastUpdateDate = center_LastUpdateDate;
    }

    public List<SubSettings> getSettings() {
        return settings;
    }

    public void setSettings(List<SubSettings> settings) {
        this.settings = settings;
    }

    private class SubSettings{
        private String id;
        private String value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
