package com.example.sport.exercise;

public class Sport {
    private String name;
    private String imageUrl;
    private int numberofsesion = 5;
    private int numberofstep = 5;
    private int icon;

    public Sport(String name, String imageUrl, int numberofsesion, int numberofstep, int icon) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.numberofsesion = numberofsesion;
        this.numberofstep = numberofstep;
        this.icon = icon;
    }

    public Sport(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public int getNumberofsesion() {
        return numberofsesion;
    }

    public void setNumberofsesion(int numberofsesion) {
        this.numberofsesion = numberofsesion;
    }

    public int getNumberofstep() {
        return numberofstep;
    }

    public void setNumberofstep(int numberofstep) {
        this.numberofstep = numberofstep;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getIcon() {
        return icon;
    }
}
