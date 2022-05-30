package com.zdn.EasyCarServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 因为数据隐蔽性，返回时在如password字段get方法上注解@JsonIgnore
 * 该字段不会被序列化和反序列化
 * 或者类上注解@JsonIgnoreProperties({"password"})
 */
@JsonIgnoreProperties({ "password"})
@Table(name = "user")
public class TUser {

    @Id
    private String userId;

    private String username;

    private String password;

    private String phone;

    private String state;

    private String is_set;


    public String getId() {
        return userId;
    }

    public void setId(String id) {
        this.userId = id ;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getState() {
        return state;
    }

    public String getIs_set() {
        return is_set;
    }

    public void setIs_set(String is_set) {
        this.is_set = is_set;
    }

    public void setState(String state) {
        this.state = state;
    }

}