package com.zdn.EasyCarServer.vo;

public class FeedActionVO {

    private String feedId;

    private String userId;


    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId == null ? null : feedId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    @Override
    public String toString() {
        return "FeedActionVO{" +
                ", feedId='" + feedId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}