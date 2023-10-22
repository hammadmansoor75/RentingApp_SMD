package com.hammadmansoor.smd_chatapp;

import java.util.List;

public class RentItemData {
    private String name;
    private String description;
    private double hourlyRate;
    private List<String> imageUrls;
    private List<String> videoUrls;
    private String userID;
    private String city;

    public RentItemData(String name, String description,String city, double hourlyRate, String userID) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.hourlyRate = hourlyRate;
//        this.imageUrls = imageUrls;
//        this.videoUrls = videoUrls;
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<String> getVideoUrls() {
        return videoUrls;
    }

    public void setVideoUrls(List<String> videoUrls) {
        this.videoUrls = videoUrls;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
