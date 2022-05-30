package com.zdn.EasyCarServer.vo;

/**
 * desc   : 用户id参数
 * version: 1.0
 */
public class UserIdVO extends PageVO {

    private String userId;

    public UserIdVO() {
    }

    public UserIdVO(PageVO pageVO) {
        setPageNum(pageVO.getPageNum());
        setPageSize(pageVO.getPageSize());
    }

    public UserIdVO(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserIdVO{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
