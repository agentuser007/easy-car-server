package com.zdn.EasyCarServer.controller;

import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.service.ViolationService;
import com.zdn.EasyCarServer.vo.ViolationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "06-Violation", value = "userCarViolation", description = "车辆违章相关接口--ok")
@RestController
@RequestMapping("/vio")
public class ViolationController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ViolationService violationService;


    @ApiOperation(value = "新增车辆违章", notes = "测试专用")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result insertVio(ViolationVO violationVO
                              // , @RequestAttribute(name = "userId") String userId
                              // TODO：注意！测试时取消api拦截，联调app记得恢复！！
    ) {
        if (violationVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + violationVO.toString());
        return violationService.insertNewViolation(violationVO);
        // return feedCommentService.saveFeedComment(feedCommentSaveVO, userId);
    }

    @ApiOperation(value = "查询所有车辆违章", notes = "查询车辆违章接口")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public Result getVio(String licence
                              // , @RequestAttribute(name = "userId") String userId
                              // TODO：注意！测试时取消api拦截，联调app记得恢复！！
    ) {
        if (licence == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + licence);
        return violationService.getViolationList(licence);
        // return feedCommentService.saveFeedComment(feedCommentSaveVO, userId);
    }

    @ApiOperation(value = "查询未读车辆违章", notes = "查询未读车辆违章接口")
    @RequestMapping(value = "/unread", method = RequestMethod.POST)
    @ResponseBody
    public Result getUnreadVio(String licence
                         // , @RequestAttribute(name = "userId") String userId
                         // TODO：注意！测试时取消api拦截，联调app记得恢复！！
    ) {
        if (licence == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + licence);
        return violationService.getUnLookViolation(licence);
        // return feedCommentService.saveFeedComment(feedCommentSaveVO, userId);
    }

    @ApiOperation(value = "更新未读车辆违章", notes = "更新未读至已读违章接口")
    @RequestMapping(value = "/isread", method = RequestMethod.POST)
    @ResponseBody
    public Result setIsReadVio(String vioId
                               // , @RequestAttribute(name = "userId") String userId
                               // TODO：注意！测试时取消api拦截，联调app记得恢复！！
    ) {
        if (vioId == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + vioId);
        return violationService.setIsLookViolation(vioId);
        // return feedCommentService.saveFeedComment(feedCommentSaveVO, userId);
    }

}
