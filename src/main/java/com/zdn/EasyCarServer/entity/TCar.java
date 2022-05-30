package com.zdn.EasyCarServer.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "car")
public class TCar {

    @Id
    private String carId;

    private String userId;

    private String licence;

    private Integer carType;

    private Integer ownerType;

    private Integer useType;

    private Date permitTime;

    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
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

    public Date getPermitTime() {
        return permitTime;
    }

    public void setPermitTime(Date permitTime) {
        this.permitTime = permitTime;
    }

    @Override
    public String toString() {
        return "TCar{" +
                "carId='" + carId + '\'' +
                ", userId='" + userId + '\'' +
                ", licence='" + licence + '\'' +
                ", carType=" + carType +
                ", owner=" + ownerType +
                ", useType=" + useType +
                ", permitTime=" + permitTime +
                '}';
    }
}
