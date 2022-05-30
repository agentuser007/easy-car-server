package com.zdn.EasyCarServer.vo;

import java.util.Date;

public class UserCarSaveVO {

    private String userId;

    private String licence;

    private Integer carType;

    private Integer ownerType;

    private Integer useType;

    private String permitTime;

    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public String getPermitTime() {
        return permitTime;
    }

    public void setPermitTime(String permitTime) {
        this.permitTime = permitTime;
    }

    @Override
    public String toString() {
        return "UserCarVO{" +
                "userId='" + userId + '\'' +
                ", licence='" + licence + '\'' +
                ", carType=" + carType +
                ", ownerType=" + ownerType +
                ", useType=" + useType +
                ", permitTime=" + permitTime +
                '}';
    }
}
