package com.intek.kalabean.Model;

import java.util.List;

public class ChainStoreList {
    private List<ChainStore> StoreList;

    public List<ChainStore> getStoreList() {
        return StoreList;
    }

    public void setStoreList(List<ChainStore> storeList) {
        StoreList = storeList;
    }

    public class ChainStore{
        private int SellCenterID;
        private String image;
        private String TitleFA;
        private String ShopCount;
        private int floorCount;
        private String Address;

        private List<SubSettings> settings;

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
            TitleFA = titleFA;
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
