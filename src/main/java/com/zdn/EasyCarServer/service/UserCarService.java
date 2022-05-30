package com.zdn.EasyCarServer.service;

import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.vo.UserCarSaveVO;

public interface UserCarService {
    Result saveCarInfo(UserCarSaveVO userCarSaveVO, String userId);
    Result getCarInfo(String userId);

}
