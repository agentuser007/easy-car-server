package com.zdn.EasyCarServer.vo;

import org.springframework.web.multipart.MultipartFile;



/**
 * desc   : 动态保存参数
 * version: 1.0
 */
public class FeedSaveVO {

    private String userId;

    private String feedInfo;

    private String feedTitle;

    private String state;

    private MultipartFile[] photo;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFeedInfo() {
        return feedInfo;
    }

    public void setFeedInfo(String feedInfo) {
        this.feedInfo = feedInfo;
    }

    public MultipartFile[] getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile[] photo) {
        this.photo = photo;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }

    @Override
    public String toString() {
        return "FeedSaveVO{" +
                "userId='" + userId + '\'' +
                ", feedInfo='" + feedInfo + '\'' +
                ", feedTitle='" + feedTitle + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
