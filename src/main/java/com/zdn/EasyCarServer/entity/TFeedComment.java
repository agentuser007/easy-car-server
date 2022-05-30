package com.zdn.EasyCarServer.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "feed_comment")
public class TFeedComment {

    @Id
    private String id;

    private String feedId;

    private String userId;

    private String commentInfo;

    private Boolean isLook;

    private Date createTime;

    private Integer state;

    public Boolean getLook() {
        return isLook;
    }

    public void setLook(Boolean look) {
        isLook = look;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

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

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo == null ? null : commentInfo.trim();
    }

    public Boolean getIsLook() {
        return isLook;
    }

    public void setIsLook(Boolean isLook) {
        this.isLook = isLook;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TFeedComment{" +
                ", feedId='" + feedId + '\'' +
                ", userId='" + userId + '\'' +
                ", commentInfo='" + commentInfo + '\'' +
                '}';
    }
}