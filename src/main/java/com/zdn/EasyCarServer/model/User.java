package com.zdn.EasyCarServer.model;

public class User {

    private String user_id;
    private String username;
    private String phone;
    private String state;
    private String is_set;

    public String getId() {
        return user_id;
    }

    public void setId(String id) {
        this.user_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIs_set() {
        return is_set;
    }

    public void setIs_set(String is_set) {
        this.is_set = is_set;
    }
}
