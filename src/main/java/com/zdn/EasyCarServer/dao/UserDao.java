package com.zdn.EasyCarServer.dao;


import com.zdn.EasyCarServer.model.User;
import com.zdn.EasyCarServer.vo.LoginVO;
import com.zdn.EasyCarServer.vo.UserSearchVO;

import java.util.List;

public interface UserDao {

    User login(LoginVO loginVO);

    //Integer getNewUid();

    Integer userTotal(UserSearchVO userSearchVO);

    List<User> pageUser(UserSearchVO userSearchVO);
}