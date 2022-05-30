package com.zdn.EasyCarServer.vo;

/**
 * desc    : 评论发布
 * version : 1.0
 */
public class FeedCommentSaveVO {

    private String feedId;
    private String userId;
    private String commentInfo;


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


    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    @Override
    public String toString() {
        return "FeedCommentSaveVO{" +
                ", feedId='" + feedId + '\'' +
                ", userId='" + userId + '\'' +
                ", commentInfo='" + commentInfo + '\'' +
                '}';
    }
}
