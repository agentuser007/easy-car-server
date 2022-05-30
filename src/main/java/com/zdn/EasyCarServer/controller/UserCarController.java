package com.zdn.EasyCarServer.controller;


import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.service.UserCarService;
import com.zdn.EasyCarServer.vo.UserCarSaveVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "05-userCar", value = "userCarApi", description = "用户车辆相关接口")
@RestController
@RequestMapping("/car")
public class UserCarController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserCarService userCarService;


    @ApiOperation(value = "用户车辆", notes = "保存车辆信息接口--ok")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result saveUserCar(UserCarSaveVO userCarSaveVO
                                  // , @RequestAttribute(name = "userId") String userId
                                  // TODO：注意！测试时取消api拦截，联调app记得恢复！！

    ) {

        if (userCarSaveVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + userCarSaveVO.toString());
        return userCarService.saveCarInfo(userCarSaveVO, userCarSaveVO.getUserId());

        // return feedCommentService.saveFeedComment(feedCommentSaveVO, userId);
    }

    @ApiOperation(value = "查询用户车辆", notes = "查询车辆信息接口--ok")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public Result userCarInfo(String userId
                                  // , @RequestAttribute(name = "userId") String userId
                                  // TODO：注意！测试时取消api拦截，联调app记得恢复！！该接口只需 userId

    ) {

        if (userId == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + userId);
        return userCarService.getCarInfo(userId);

        // return feedCommentService.saveFeedComment(feedCommentSaveVO, userId);
    }

}
