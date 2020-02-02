package com.zachrawi.ceriwit;

public class Ceriwit {
    String id;
    String username;
    String message;

    public Ceriwit(String id, String username, String message) {
        this.id = id;
        this.username = username;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
