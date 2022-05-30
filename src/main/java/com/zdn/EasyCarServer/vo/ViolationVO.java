package com.zdn.EasyCarServer.vo;

public class ViolationVO {

    private String licence;
    private String action;
    private String location;
    private String punish;
    private String isLook;
    private String state;

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPunish() {
        return punish;
    }

    public void setPunish(String punish) {
        this.punish = punish;
    }

    public String getIsLook() {
        return isLook;
    }

    public void setIsLook(String isLook) {
        this.isLook = isLook;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ViolationVO{" +
                "licence='" + licence + '\'' +
                ", action='" + action + '\'' +
                ", location='" + location + '\'' +
                ", punish='" + punish + '\'' +
                ", isLook='" + isLook + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
