package com.example.Soup.model;

public class SoupData {
    private String soupName;
    private String soupDesc;
    private String imageAddress;

    public SoupData(String soupName, String soupDesc, String imageAddress) {
        this.soupName = soupName;
        this.soupDesc = soupDesc;
        this.imageAddress = imageAddress;
    }

    public SoupData() {
        this("Database Error Soup", "Made from bad data in the database", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Arabic_Question_mark_%28RTL%29.svg/90px-Arabic_Question_mark_%28RTL%29.svg.png");
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
