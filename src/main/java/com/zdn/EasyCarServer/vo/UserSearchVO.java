package com.zdn.EasyCarServer.vo;

/**
 * desc   : 用户查询参数
 * version: 1.0
 */
public class UserSearchVO extends PageVO {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
