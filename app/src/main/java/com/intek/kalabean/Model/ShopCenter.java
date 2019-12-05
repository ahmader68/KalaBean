package com.intek.kalabean.Model;

import java.util.List;

public class ShopCenter {
    private int id;
    private String TitleFA;
    private String Description;
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
    private String data_lat;
    private String data_lng;
    private String url;
    private String description;
    private String icon;
    private String Address;
    private int SellCenterID;
    private int FloorId;
   private int JobCatid;
   private String Tel;

    public String getData_lat() {
        return data_lat;
    }

    public void setData_lat(String data_lat) {
        this.data_lat = data_lat;
    }

    public String getData_lng() {
        return data_lng;
    }

    public void setData_lng(String data_lng) {
        this.data_lng = data_lng;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getSellCenterID() {
        return SellCenterID;
    }

    public void setSellCenterID(int sellCenterID) {
        SellCenterID = sellCenterID;
    }

    public int getFloorId() {
        return FloorId;
    }

    public void setFloorId(int floorId) {
        FloorId = floorId;
    }

    public int getJobCatid() {
        return JobCatid;
    }

    public void setJobCatid(int jobCatid) {
        JobCatid = jobCatid;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String descriptionFA) {
        Description = descriptionFA;
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
