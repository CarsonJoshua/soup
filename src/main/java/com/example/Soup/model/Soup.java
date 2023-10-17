package com.example.Soup.model;


public class Soup {
    private String soupId;
    private String soupName;
    private String soupDesc;
    private String imageAddress;

    public Soup(String soupId, String soupName, String soupDesc, String imageAddress) {
        this.soupId = soupId;
        this.soupName = soupName;
        this.soupDesc = soupDesc;
        this.imageAddress = imageAddress;
    }

    public Soup(String soupId, SoupData soupData) {
        this(soupId, soupData.getSoupName(), soupData.getSoupDesc(), soupData.getImageAddress());
    }
//    public Soup(String soupName, String soupDesc, String imageAddress) {
//        this.soupName = soupName;
//        this.soupDesc = soupDesc;
//        this.imageAddress = imageAddress;
//    }


    public String getSoupId() {
        return soupId;
    }

    public void setSoupId(String soupId) {
        this.soupId = soupId;
    }

    public String getSoupName() {
        return soupName;
    }

    public void setSoupName(String soupName) {
        this.soupName = soupName;
    }

    public String getSoupDesc() {
        return soupDesc;
    }

    public void setSoupDesc(String soupDesc) {
        this.soupDesc = soupDesc;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }
}
