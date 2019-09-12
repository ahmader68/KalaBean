package com.intek.kalabean.Model;

import java.util.List;

public class ComplexList {
    private List<Complex> StoreList;

    public List<Complex> getStoreList() {
        return StoreList;
    }

    public void setStoreList(List<Complex> storeList) {
        StoreList = storeList;
    }

    public class Complex {
        private int SellCenterID;
        private String TitleFA;
        private String image;
        private String ShopCount;
        private String Address;
        private List<SubSettings> settings;

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

        public String getShopCount() {
            return ShopCount;
        }

        public void setShopCount(String shopCount) {
            ShopCount = shopCount;
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
