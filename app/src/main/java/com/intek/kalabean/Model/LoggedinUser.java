package com.intek.kalabean.Model;

import java.util.List;

public class LoggedinUser {
    List<SubUser> items;

    public List<SubUser> getItems() {
        return items;
    }

    public void setItems(List<SubUser> items) {
        this.items = items;
    }

    public class SubUser{
        private int result;
        private String firstName;
        private String lastName;
        private String FullName;
        private String userName;
        private String fatherName;
        private int userLevel;
        private String email;
        private String tel;
        private String mobile;
        private String address;
        private boolean LogicallyDeleted;
        private String province;
        private String provinceTitle;
        private String city;
        private String workTel;
        private String nationalCode;
        private String postalCode;
        private int CreatorId;
        private String BirthCertificate;
        private String BirthCity;
        private String CreateDate;
        private boolean Status;
        private boolean gender;
        private String birthDate;
        private String ImageUrl;
        private String education;
        private String reshteh;
        private String personalNo;
        private int ShopId;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String fullName) {
            FullName = fullName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFatherName() {
            return fatherName;
        }

        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }

        public int getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(int userLevel) {
            this.userLevel = userLevel;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public boolean isLogicallyDeleted() {
            return LogicallyDeleted;
        }

        public void setLogicallyDeleted(boolean logicallyDeleted) {
            LogicallyDeleted = logicallyDeleted;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvinceTitle() {
            return provinceTitle;
        }

        public void setProvinceTitle(String provinceTitle) {
            this.provinceTitle = provinceTitle;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getWorkTel() {
            return workTel;
        }

        public void setWorkTel(String workTel) {
            this.workTel = workTel;
        }

        public String getNationalCode() {
            return nationalCode;
        }

        public void setNationalCode(String nationalCode) {
            this.nationalCode = nationalCode;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public int getCreatorId() {
            return CreatorId;
        }

        public void setCreatorId(int creatorId) {
            CreatorId = creatorId;
        }

        public String getBirthCertificate() {
            return BirthCertificate;
        }

        public void setBirthCertificate(String birthCertificate) {
            BirthCertificate = birthCertificate;
        }

        public String getBirthCity() {
            return BirthCity;
        }

        public void setBirthCity(String birthCity) {
            BirthCity = birthCity;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String createDate) {
            CreateDate = createDate;
        }

        public boolean isStatus() {
            return Status;
        }

        public void setStatus(boolean status) {
            Status = status;
        }

        public boolean isGender() {
            return gender;
        }

        public void setGender(boolean gender) {
            this.gender = gender;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String imageUrl) {
            ImageUrl = imageUrl;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getReshteh() {
            return reshteh;
        }

        public void setReshteh(String reshteh) {
            this.reshteh = reshteh;
        }

        public String getPersonalNo() {
            return personalNo;
        }

        public void setPersonalNo(String personalNo) {
            this.personalNo = personalNo;
        }

        public int getShopId() {
            return ShopId;
        }

        public void setShopId(int shopId) {
            ShopId = shopId;
        }
    }
}
