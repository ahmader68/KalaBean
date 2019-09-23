package com.intek.kalabean.Model;

import java.util.List;

public class ProductList {

    private List<Product> items;

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public class Product {
        private int id;
        private String Title;
        private String Brief;
        private String Content;
        private String Tag;
        private String Language;
        private String Active;
        private int CategoryId;
        private String CategoryName;
        private int SubCategoryId;
        private String SubCategoryName;
        private String Producer;
        private String CreateDate;
        private String Coverimage;
        private List<Album> AlbumImages;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getBrief() {
            return Brief;
        }

        public void setBrief(String brief) {
            Brief = brief;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }

        public String getTag() {
            return Tag;
        }

        public void setTag(String tag) {
            Tag = tag;
        }

        public String getLanguage() {
            return Language;
        }

        public void setLanguage(String language) {
            Language = language;
        }

        public String getActive() {
            return Active;
        }

        public void setActive(String active) {
            Active = active;
        }

        public int getCategoryId() {
            return CategoryId;
        }

        public void setCategoryId(int categoryId) {
            CategoryId = categoryId;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String categoryName) {
            CategoryName = categoryName;
        }

        public int getSubCategoryId() {
            return SubCategoryId;
        }

        public void setSubCategoryId(int subCategoryId) {
            SubCategoryId = subCategoryId;
        }

        public String getSubCategoryName() {
            return SubCategoryName;
        }

        public void setSubCategoryName(String subCategoryName) {
            SubCategoryName = subCategoryName;
        }

        public String getProducer() {
            return Producer;
        }

        public void setProducer(String producer) {
            Producer = producer;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String createDate) {
            CreateDate = createDate;
        }

        public String getCoverimage() {
            return Coverimage;
        }

        public void setCoverimage(String coverimage) {
            Coverimage = coverimage;
        }

        public List<Album> getAlbumImages() {
            return AlbumImages;
        }

        public void setAlbumImages(List<Album> albumImages) {
            AlbumImages = albumImages;
        }

        class Album {
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
