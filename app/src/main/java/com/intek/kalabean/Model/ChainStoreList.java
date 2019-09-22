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
        private int Shopid;
        private int SellCenterID;
        private int Cityid;
        private int JobCatid;
        private int VisiteCount;
        private int PercentTakhfif;
        private int PriceTakhfif;
        private int Status;
        private String image;
        private String TitleFA;
        private String TitleEN;
        private String TitleAR;
        private String ShopCount;
        private String Tel;
        private int floorCount;
        private String Address;
        private int SellCenterCatID;
        private int PercentHaraj;
        private String DescriptionFA;
        private String DescriptionEN;
        private String DescriptionAR;
        private String AddressFA;
        private String AddressEN;
        private String AddressAR;
        private String pelak;
        private String toor;
        private String linksite;
        private List<SubSettings> settings;

        public int getShopid() {
            return Shopid;
        }

        public void setShopid(int shopid) {
            Shopid = shopid;
        }

        public int getCityid() {
            return Cityid;
        }

        public void setCityid(int cityid) {
            Cityid = cityid;
        }

        public int getJobCatid() {
            return JobCatid;
        }

        public void setJobCatid(int jobCatid) {
            JobCatid = jobCatid;
        }

        public int getVisiteCount() {
            return VisiteCount;
        }

        public void setVisiteCount(int visiteCount) {
            VisiteCount = visiteCount;
        }

        public int getPercentTakhfif() {
            return PercentTakhfif;
        }

        public void setPercentTakhfif(int percentTakhfif) {
            PercentTakhfif = percentTakhfif;
        }

        public int getPriceTakhfif() {
            return PriceTakhfif;
        }

        public void setPriceTakhfif(int priceTakhfif) {
            PriceTakhfif = priceTakhfif;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int status) {
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

        public String getTel() {
            return Tel;
        }

        public void setTel(String tel) {
            Tel = tel;
        }

        public int getPercentHaraj() {
            return PercentHaraj;
        }

        public void setPercentHaraj(int percentHaraj) {
            PercentHaraj = percentHaraj;
        }

        public String getDescriptionFA() {
            return DescriptionFA;
        }

        public void setDescriptionFA(String descriptionFA) {
            DescriptionFA = descriptionFA;
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

        public String getAddressFA() {
            return AddressFA;
        }

        public void setAddressFA(String addressFA) {
            AddressFA = addressFA;
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

        public String getPelak() {
            return pelak;
        }

        public void setPelak(String pelak) {
            this.pelak = pelak;
        }

        public String getToor() {
            return toor;
        }

        public void setToor(String toor) {
            this.toor = toor;
        }

        public String getLinksite() {
            return linksite;
        }

        public void setLinksite(String linksite) {
            this.linksite = linksite;
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
