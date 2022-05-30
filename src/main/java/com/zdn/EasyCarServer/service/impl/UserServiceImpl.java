package com.zdn.EasyCarServer.service.impl;

import com.zdn.EasyCarServer.dao.UserDao;
import com.zdn.EasyCarServer.entity.TUser;
import com.zdn.EasyCarServer.mapper.TUserMapper;
import com.zdn.EasyCarServer.model.PageInfo;
import com.zdn.EasyCarServer.model.User;
import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.service.UserService;
import com.zdn.EasyCarServer.util.ParamUtil;
import com.zdn.EasyCarServer.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * desc   : 用户相关
 * version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TUserMapper tUserMapper;

    @Resource
    private UserDao userDao;

    @Override
    public Result register(RegisterVO registerVO) {
        Result result = Result.success();

        String username = registerVO.getUsername();
        String phone = registerVO.getPhone();
        String password = registerVO.getPassword();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            result.setCodeAndMsg("00103", "用户名或手机号或密码为空");
            return result;
        }

        // 检测手机号
        TUser param = new TUser();
        param.setPhone(phone);
        TUser tUser = tUserMapper.selectOne(param);
        if (tUser != null) {
            result.setCodeAndMsg("00105", "手机号" + phone + "已注册");
            return result;
        }
        // 检测用户名
        param.setPhone(null);
        param.setUsername(username);
        tUser = tUserMapper.selectOne(param);
        if (tUser != null) {
            result.setCodeAndMsg("00106", "用户名" + username + "已存在");
            return result;
        }

        tUser = new TUser();
        tUser.setId(ParamUtil.getUUID());
        tUser.setUsername(username);
        // 保存信息
        tUser.setPassword(password);
        tUser.setPhone(registerVO.getPhone());
        tUser.setPhone(tUser.getPhone());
        tUserMapper.insertSelective(tUser);

        result.setData(tUser);
        return result;
    }

    @Override
    public Result login(LoginVO loginVO) {
        Result result = Result.success();

        String username = loginVO.getUsername();
        String password = loginVO.getPassword();
/*
        if (StringUtils.hasText(username) || StringUtils.hasText(password)) {
            result.setCodeAndMsg("00103", "用户名或密码为空");
            return result;
        }
*/

        // 用户名登录
        TUser param = new TUser();
        param.setUsername(username);
        param.setPassword(password);
        TUser tUser = tUserMapper.selectOne(param);

        // 支持手机号登录
        if (tUser == null) {
            param.setUsername(null);
            param.setPhone(username);
            tUser = tUserMapper.selectOne(param);
        }
        if (tUser == null) {
            result.setCodeAndMsg("00104", "用户名或密码错误");
            return result;
        }
        if (Integer.parseInt(tUser.getState()) == 2) {
            result.setCodeAndMsg("00105", "用户已失效");
            return result;
        }

        result.setData(tUser);
        return result;
    }

    @Override
    public Result resetPassword(RegisterVO registerVO) {
        Result result = Result.success();

        String username = registerVO.getUsername();
        String phone = registerVO.getPhone();
        String password = registerVO.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            result.setCodeAndMsg("00103", "用户名或手机号或密码为空");
            return result;
        }

        // 检查用户存不存在
        TUser param = new TUser();
        param.setUsername(username);
        param.setPhone(phone);
        TUser tUser = tUserMapper.selectOne(param);

        if (tUser == null) {
            result.setCodeAndMsg("00104", "用户不存在");
            return result;
        }else if(tUser.getState().equals("2")){
            result.setCodeAndMsg("00104", "用户已失效");
            return result;
        }
        tUser.setPassword(password);
        tUserMapper.updateByPrimaryKeySelective(tUser);
        // 手机号
        tUser.setPhone(tUser.getPhone());

        result.setData(tUser);
        return result;
    }

    @Override
    public Result updateUser(UserUpdateVO userUpdateVO, String userId) {
        Result result = Result.success();

        String phone = userUpdateVO.getPhone();
        String username = userUpdateVO.getUsername();
        String password = userUpdateVO.getPassword();
        if (StringUtils.isEmpty(phone) && StringUtils.isEmpty(username) && StringUtils.isEmpty(password) ) {
            result.setCodeAndMsg("00104", "选填参数为空");
            return result;
        }

        TUser param = new TUser();

        TUser temp;
        // 检测手机号
        if (!StringUtils.isEmpty(phone)) {
            param.setPhone(phone);
            temp = tUserMapper.selectOne(param);
            if (temp != null) {
                result.setCodeAndMsg("00105", "手机号" + phone + "已绑定");
                return result;
            }
        }
        // 检用户名
        if (!StringUtils.isEmpty(username)) {
            param.setPhone(null);
            param.setUsername(username);
            temp = tUserMapper.selectOne(param);
            if (temp != null) {
                result.setCodeAndMsg("00106", "用户名" + username + "已存在");
                return result;
            }
        }

        // 切换数据
        BeanUtils.copyProperties(userUpdateVO, param);
        param.setId(userId);
        tUserMapper.updateByPrimaryKeySelective(param);

        temp = tUserMapper.selectByPrimaryKey(userId);

        // 手机号
        temp.setPhone(temp.getPhone());

        result.setData(temp);
        return result;
    }

    @Override
    public Result getUser(IdVO userId) {
        Result result = Result.success();

        //id = userId;

        TUser tUser = tUserMapper.selectByPrimaryKey(userId.getId());
        if (tUser == null) {
            result.setCodeAndMsg("00102", "没有该用户");
            return result;
        }

        // 隐藏手机号
        tUser.setPhone(tUser.getPhone());

        result.setData(tUser);
        return result;
    }


    @Override
    public Result searchUser(UserSearchVO userSearchVO) {
        Result result = Result.success();

        TUser tUser = null;

        String username = userSearchVO.getUsername();
        if (!StringUtils.isEmpty(username)) {
            TUser param = new TUser();
            param.setUsername(username);
            tUser = tUserMapper.selectOne(param);
        }

        if (tUser == null) {
            result.setCodeAndMsg("00102", "没有该用户");
            return result;
        }

        // 隐藏手机号
        tUser.setPhone(tUser.getPhone());

        result.setData(tUser);
        return result;
    }

    @Override
    public TUser userInfo(String id) {
        return tUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public Result queryUser(UserSearchVO userSearchVO) {
        Result result = Result.success();

        ParamUtil.setPage(userSearchVO);
        Integer total = 0;
        List<User> userList = new ArrayList<>();

        String username = userSearchVO.getUsername();
        if (!StringUtils.isEmpty(username)) {
            total = userDao.userTotal(userSearchVO);
            userList = userDao.pageUser(userSearchVO);
        }

        // 分页数据
        PageInfo<User> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(userSearchVO.getPageNum());
        pageInfo.setPageSize(userSearchVO.getPageSize());
        pageInfo.setTotal(total);
        pageInfo.setList(userList);
        pageInfo.setSize(userList == null ? 0 : userList.size());

        result.setData(pageInfo);
        return result;
    }

}
