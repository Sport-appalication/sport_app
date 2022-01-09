package com.example.sport;

public class Sport {
    private String name;
    private String description;
    private String imageUrl;
    private int numberofsesion = 5;
    private int numberofstep = 5;
    private int icon;

    public Sport(String name, String description,String imageUrl,int icon) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.icon = icon;
    }
    public Sport(String name, String description,int icon) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getIcon() {
        return icon;
    }
}
