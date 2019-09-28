package com.intek.kalabean.Model;

import java.util.List;

public class Positions {
    private List<Category> items;

    public List<Category> getItems() {
        return items;
    }

    public void setItems(List<Category> items) {
        this.items = items;
    }

    public class Category{
        private int id;
        private String name;
        private List<SubCategory> SubCategory;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Positions.SubCategory> getSubCategory() {
            return SubCategory;
        }

        public void setSubCategory(List<Positions.SubCategory> subCategory) {
            SubCategory = subCategory;
        }
    }
    public class SubCategory{
        private int idParent;
        private int idSubCategory;
        private String nameSubCategory;

        public int getIdParent() {
            return idParent;
        }

        public void setIdParent(int idParent) {
            this.idParent = idParent;
        }

        public int getIdSubCategory() {
            return idSubCategory;
        }

        public void setIdSubCategory(int idSubCategory) {
            this.idSubCategory = idSubCategory;
        }

        public String getNameSubCategory() {
            return nameSubCategory;
        }

        public void setNameSubCategory(String nameSubCategory) {
            this.nameSubCategory = nameSubCategory;
        }
    }
}

