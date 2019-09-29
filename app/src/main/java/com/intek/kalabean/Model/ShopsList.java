package com.intek.kalabean.Model;

import java.util.List;

public class ShopsList {

    private List<Shops> items;

    public List<Shops> getItems() {
        return items;
    }

    public void setItems(List<Shops> items) {
        this.items = items;
    }

    public class Shops {
        private int Shopid;
        private int SellCenterID;
        private String TitleFA;
        private String image;

        public int getShopid() {
            return Shopid;
        }

        public void setShopid(int shopid) {
            Shopid = shopid;
        }

        public int getSellCenterID() {
            return SellCenterID;
        }

        public void setSellCenterID(int sellCenterID) {
            SellCenterID = sellCenterID;
        }

        public String getTitleFA() {
            return TitleFA;
        }

        public void setTitleFA(String titleFA) {
            TitleFA = titleFA;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }


}
