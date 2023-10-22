package com.hammadmansoor.smd_chatapp;

public class ItemModel {
    String name,hourlyRate,date;

    public ItemModel(String name, String hourlyRate, String date) {
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(String hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
