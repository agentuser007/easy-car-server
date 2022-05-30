package com.zdn.EasyCarServer.service;

import com.zdn.EasyCarServer.entity.TUser;
import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.vo.*;

public interface UserService {

    Result register(RegisterVO registerVO);

    Result login(LoginVO loginVO);

    Result resetPassword(RegisterVO registerVO);

    Result updateUser(UserUpdateVO userUpdateVO, String userId);

    Result getUser(IdVO userId);

    Result searchUser(UserSearchVO userSearchVO);

    TUser userInfo(String id);

    Result queryUser(UserSearchVO userSearchVO);
}
