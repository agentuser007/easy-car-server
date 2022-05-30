package com.zdn.EasyCarServer.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "feed")
public class TFeed {

    @Id
    private String feedId;

    private String userId;

    private String feedInfo;

    private String feedTitle;

    private String state;

    private String url;

    private Date createTime;

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFeedInfo() {
        return feedInfo;
    }

    public void setFeedInfo(String feedInfo) {
        this.feedInfo = feedInfo == null ? null : feedInfo.trim();
    }

}