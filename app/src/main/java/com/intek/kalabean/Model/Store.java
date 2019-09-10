package com.intek.kalabean.Model;

import android.util.ArrayMap;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Store {
    private String image;
    private String TitleFA;
    private String ShopCount;
    private int floorCount;
    private String Address;

    private List<SubSettings> settings;

    private boolean stair;
    private boolean cafe;
    private boolean wc;
    private boolean gift;
    private boolean parking;
    private boolean play;
    private boolean shop;
    private boolean wifi;
    private boolean elevator;

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

    public List<SubSettings> getSettings() {
        return settings;
    }

    public void setSettings(List<SubSettings> settings) {
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
