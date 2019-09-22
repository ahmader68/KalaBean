package com.intek.kalabean.Model;

public class AddProduct {
    private int CategoryId;
    private int SubCategoryId;
    private int Producer;
    private int AccessLevel;
    private int Price;
    private int Discount;
    private int OrderNo;
    private int Quantity;
    private String TitleFA;
    private String Brand;
    private String Model;
    private String Series;
    private String Attributes;
    private String Brief;
    private String Manufacturer;
    private boolean IsSuggested;

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public int getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        SubCategoryId = subCategoryId;
    }

    public int getProducer() {
        return Producer;
    }

    public void setProducer(int producer) {
        Producer = producer;
    }

    public int getAccessLevel() {
        return AccessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        AccessLevel = accessLevel;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int discount) {
        Discount = discount;
    }

    public int getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(int orderNo) {
        OrderNo = orderNo;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getTitleFA() {
        return TitleFA;
    }

    public void setTitleFA(String titleFA) {
        TitleFA = titleFA;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getSeries() {
        return Series;
    }

    public void setSeries(String series) {
        Series = series;
    }

    public String getAttributes() {
        return Attributes;
    }

    public void setAttributes(String attributes) {
        Attributes = attributes;
    }

    public String getBrief() {
        return Brief;
    }

    public void setBrief(String brief) {
        Brief = brief;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public boolean isSuggested() {
        return IsSuggested;
    }

    public void setSuggested(boolean suggested) {
        IsSuggested = suggested;
    }
}
