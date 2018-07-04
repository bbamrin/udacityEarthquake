package com.example.nick.earthquakeapp;

public class Earthquake {
    private String direction;
    private String exactPlace;

    public String getExactPlace() {
        return exactPlace;
    }

    public void setExactPlace(String exactPlace) {
        this.exactPlace = exactPlace;
    }

    private String date;
    private String magnitude;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }
}
