package com.intek.kalabean.Model;

import java.util.List;

public class SearchedProduct {

    private List<SearchedProducts> items;
    private List<Setting> settings;

    public List<SearchedProducts> getItems() {
        return items;
    }

    public void setItems(List<SearchedProducts> items) {
        this.items = items;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    public class SearchedProducts{
        private int id;
        private String link;
        private String image;
        private String Title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }
    }

    public class Setting{
        private int itemsCount;

        public int getItemsCount() {
            return itemsCount;
        }

        public void setItemsCount(int itemsCount) {
            this.itemsCount = itemsCount;
        }
    }
}
