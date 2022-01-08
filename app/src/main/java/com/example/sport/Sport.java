package com.example.sport;

public class Sport {
    private String name;
    private String description;
    private int numberofsesion;
    private int numberofstep;

    public Sport(String name, String description) {
        this.name = name;
        this.description = description;
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
}
