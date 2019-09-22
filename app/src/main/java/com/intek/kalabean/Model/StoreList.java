package com.intek.kalabean.Model;

import java.util.List;

public class StoreList {

    List<Store> StoreList;

    public List<Store> getStoreList() {
        return StoreList;
    }

    public void setStoreList(List<Store> StoreList) {
        this.StoreList = StoreList;
    }

    public class Store {
        private int SellCenterID;
        private String image;
        private String TitleFA;
        private String TitleEN;
        private String TitleAR;

        private String ShopCount;
        private int floorCount;
        private String Address;
        private String AddressEN;
        private String AddressAR;
        private String Description;
        private String DescriptionEN;
        private String DescriptionAR;
        private int CityId;
        private boolean HasHaraj;
        private String toor;

        private int SellCenterCatID;
        private List<SubSettings> settings;
        private double data_lat;
        private double data_lng;
        private String url;
        private boolean stair;
        private boolean cafe;
        private boolean wc;
        private boolean gift;
        private boolean parking;
        private boolean play;
        private boolean shop;
        private boolean wifi;
        private boolean elevator;

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

        public String getAddressEN() {
            return AddressEN;
        }

        public void setAddressEN(String addressEN) {
            AddressEN = addressEN;
        }

        public String getAddressAR() {
            return AddressAR;
        }

        public void setAddressAR(String addressAR) {
            AddressAR = addressAR;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getDescriptionEN() {
            return DescriptionEN;
        }

        public void setDescriptionEN(String descriptionEN) {
            DescriptionEN = descriptionEN;
        }

        public String getDescriptionAR() {
            return DescriptionAR;
        }

        public void setDescriptionAR(String descriptionAR) {
            DescriptionAR = descriptionAR;
        }

        public int getCityId() {
            return CityId;
        }

        public void setCityId(int cityId) {
            CityId = cityId;
        }

        public boolean isHasHaraj() {
            return HasHaraj;
        }

        public void setHasHaraj(boolean hasHaraj) {
            HasHaraj = hasHaraj;
        }

        public String getToor() {
            return toor;
        }

        public void setToor(String toor) {
            this.toor = toor;
        }

        public double getData_lat() {
            return data_lat;
        }

        public void setData_lat(double data_lat) {
            this.data_lat = data_lat;
        }

        public double getData_lng() {
            return data_lng;
        }

        public void setData_lng(double data_lng) {
            this.data_lng = data_lng;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSellCenterCatID() {
            return SellCenterCatID;
        }

        public void setSellCenterCatID(int sellCenterCatID) {
            SellCenterCatID = sellCenterCatID;
        }

        public int getSellCenterID() {
            return SellCenterID;
        }

        public void setSellCenterID(int sellCenterID) {
            SellCenterID = sellCenterID;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitleFA() {
            return TitleFA;
        }

        public void setTitleFA(String titleFA) {
            this.TitleFA = titleFA;
        }

        public String getShopCount() {
            return ShopCount;
        }

        public void setShopCount(String shopCount) {
            ShopCount = shopCount;
        }

        public int getFloorCount() {
            return floorCount;
        }

        public void setFloorCount(int floorCount) {
            this.floorCount = floorCount;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public List<StoreList.Store.SubSettings> getSettings() {
            return settings;
        }

        public void setSettings(List<StoreList.Store.SubSettings> settings) {
            this.settings = settings;
        }

        public boolean isStair() {
            return stair;
        }

        public void setStair(boolean stair) {
            this.stair = stair;
        }

        public boolean isCafe() {
            return cafe;
        }

        public void setCafe(boolean cafe) {
            this.cafe = cafe;
        }

        public boolean isWc() {
            return wc;
        }

        public void setWc(boolean wc) {
            this.wc = wc;
        }

        public boolean isGift() {
            return gift;
        }

        public void setGift(boolean gift) {
            this.gift = gift;
        }

        public boolean isParking() {
            return parking;
        }

        public void setParking(boolean parking) {
            this.parking = parking;
        }

        public boolean isPlay() {
            return play;
        }

        public void setPlay(boolean play) {
            this.play = play;
        }

        public boolean isShop() {
            return shop;
        }

        public void setShop(boolean shop) {
            this.shop = shop;
        }

        public boolean isWifi() {
            return wifi;
        }

        public void setWifi(boolean wifi) {
            this.wifi = wifi;
        }

        public boolean isElevator() {
            return elevator;
        }

        public void setElevator(boolean elevator) {
            this.elevator = elevator;
        }

        public class SubSettings {
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
}
