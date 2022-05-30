package com.zdn.EasyCarServer.vo;

/**
 * desc   : 动态列表参数
 * version: 1.0
 */
public class FeedSearchVO extends PageVO {

    private String userId;
    private String searchUserId;
    private String state;

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

    public String getSearchUserId() {
        return searchUserId;
    }

    public void setSearchUserId(String searchUserId) {
        this.searchUserId = searchUserId;
    }

    @Override
    public String toString() {
        return "{" +
                "searchUserId='" + searchUserId + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
