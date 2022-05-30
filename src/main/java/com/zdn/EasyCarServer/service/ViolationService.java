package com.zdn.EasyCarServer.service;

import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.vo.ViolationVO;

public interface ViolationService {

    Result setIsLookViolation(String vioId);

    Result getUnLookViolation(String licence);

    Result getViolationList(String licence);

    Result insertNewViolation(ViolationVO violationVO);

}
