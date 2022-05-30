package com.zdn.EasyCarServer.vo;

/**
 * desc   : 动态评论分页
 * version: 1.0
 */
public class FeedCommentVO extends PageVO {

    private String feedId;

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    @Override
    public String toString() {
        return "FeedCommentVO{" +
                "pageNum=" + pageNum +
                ", feedId='" + feedId + '\'' +
                ", pageSize=" + pageSize +
                '}';
    }
}
