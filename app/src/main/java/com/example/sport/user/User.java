package com.example.sport.user;

public class User {
    private String username;
    private String email;
    private int id;
    private int level;

    public User(String username, String email, int level) {
        this.username = username;
        this.email = email;
        this.level = level;
    }

    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
