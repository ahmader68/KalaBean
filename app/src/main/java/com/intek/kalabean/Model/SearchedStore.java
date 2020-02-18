package com.intek.kalabean.Model;

import java.util.List;

public class SearchedStore {

    private List<SearchedStores> items;
    private List<Setting> settings;

    public List<SearchedStores> getItems() {
        return items;
    }

    public void setItems(List<SearchedStores> items) {
        this.items = items;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    public class SearchedStores{
        private int id;
        private String TitleFA;
        private String link;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitleFA() {
            return TitleFA;
        }

        public void setTitleFA(String titleFA) {
            TitleFA = titleFA;
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
    }

    public class Setting{
        int itemCount;

        public int getItemCount() {
            return itemCount;
        }

        public void setItemCount(int itemCount) {
            this.itemCount = itemCount;
        }
    }
}
