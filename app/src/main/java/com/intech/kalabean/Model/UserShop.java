package com.intech.kalabean.Model;

import java.util.List;

public class UserShop {
    private List<UserShopFeature> items;

    public List<UserShopFeature> getItems() {
        return items;
    }

    public void setItems(List<UserShopFeature> items) {
        this.items = items;
    }

    public class UserShopFeature {
        private int id;
        private String icon;
        private String title;
        private String description;
        private String address;
        private String Tel;
        private String body;
        private String Album;

        public int getId() {
            return id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String tel) {
            Tel = tel;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getAlbum() {
            return Album;
        }

        public void setAlbum(String album) {
            Album = album;
        }
    }
}
