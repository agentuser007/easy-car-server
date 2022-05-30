package com.zdn.EasyCarServer.model;

import java.util.Date;
import java.util.List;

/**
 * 动态
 */
public class Feed {

    private String feedId;
    /**
     * 用户信息
     */
    private User user;
    private String feedInfo;
    private String feedTitle;
    private Integer viewNum;
    /**
     * 评论数
     */
    private Integer commentNum;
    /**
     * 当前用户是否点赞
     */
    private boolean like;
    /**
     * 点赞列表
     */
    private Integer likeNum;
    private List<Like> likeList;
    /**
     * 相册
     */
    private String url;
    private Date createTime;

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFeedInfo() {
        return feedInfo;
    }

    public void setFeedInfo(String feedInfo) {
        this.feedInfo = feedInfo;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public List<Like> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList;
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


    @Override
    public String toString() {
        return "Feed{" +
                "feedId='" + feedId + '\'' +
                ", user=" + user +
                ", feedInfo='" + feedInfo + '\'' +
                ", viewNum=" + viewNum +
                ", commentNum=" + commentNum +
                ", like=" + like +
                ", likeList=" + likeList +
                ", url=" + url +
                ", createTime=" + createTime +
                '}';
    }
}
