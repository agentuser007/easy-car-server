package com.zdn.EasyCarServer.controller;

import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.service.UserService;
import com.zdn.EasyCarServer.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "01-user", value = "UserApi", description = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    @ApiOperation(value = "账号注册", notes = "账号注册接口")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(RegisterVO registerVO) {
        if (registerVO == null) {
            return Result.paramIsNull();
        }
        logger.info("param is :" + registerVO.toString());
        return userService.register(registerVO);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录接口--ok")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(LoginVO loginVO) {
        if (loginVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + loginVO.toString());

        return userService.login(loginVO);
    }

    @ApiOperation(value = "密码重置", notes = "密码重置接口--ok")
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public Result resetPassword(RegisterVO registerVO) {
        if (registerVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + registerVO.toString());

        return userService.resetPassword(registerVO);
    }

    @ApiOperation(value = "用户资料更改", notes = "用户资料更改接口--postman测试")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUser(UserUpdateVO userUpdateVO
            ,  @RequestAttribute(name = "userId") String userId) {
        if (userUpdateVO == null) {
            return Result.paramIsNull();
        }

        logger.info("userId is {}, param is {}", userId, userUpdateVO.toString());

        return userService.updateUser(userUpdateVO, userId);
    }

    @ApiOperation(value = "用户信息查询", notes = "用户信息查询接口--ok")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public Result getUser(IdVO idVO) {
        if (idVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" +"");

        return userService.getUser(idVO);
    }


    @ApiOperation(value = "精准搜索用户", notes = "精准搜索用户接口")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public Result searchUser(UserSearchVO userSearchVO) {
        if (userSearchVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + userSearchVO.toString());

        return userService.searchUser(userSearchVO);
    }

    @ApiOperation(value = "模糊查询用户", notes = "模糊查询用户接口")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Result queryUser(UserSearchVO userSearchVO) {
        if (userSearchVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + userSearchVO.toString());

        return userService.queryUser(userSearchVO);
    }
}
