package com.zdn.EasyCarServer.service.impl;

import com.zdn.EasyCarServer.constant.Constant;
import com.zdn.EasyCarServer.entity.TCar;
import com.zdn.EasyCarServer.mapper.TUserCarMapper;
import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.service.UserCarService;
import com.zdn.EasyCarServer.util.ParamUtil;
import com.zdn.EasyCarServer.vo.UserCarSaveVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 保存用户车辆信息
 */
@Service
public class UserCarServiceImpl implements UserCarService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TUserCarMapper tUserCarMapper;


    @Override
    public Result saveCarInfo(UserCarSaveVO userCarSaveVO, String userId) {
        Result result = Result.success();

        // 参数验证
        String licence = userCarSaveVO.getLicence();
        if (StringUtils.isEmpty(licence)) {
            logger.warn("param warn : 必填参数不全");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "必填参数不全");
            return result;
        }

        // 是否已操作
        TCar param = new TCar();
        param.setLicence(licence);
        param.setUserId(userId);
        TCar temp = tUserCarMapper.selectOne(param);

        if (temp == null) {
            String id = ParamUtil.getUUID();
            param.setCarId(id);
            param.setCarType(userCarSaveVO.getCarType());
            param.setOwnerType(userCarSaveVO.getOwnerType());
            param.setUseType(userCarSaveVO.getUseType());
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date date = df.parse(userCarSaveVO.getPermitTime());
                param.setPermitTime(date);
                tUserCarMapper.insertSelective(param);
                temp = tUserCarMapper.selectByPrimaryKey(id);
                // 返回
                result.setData(temp);

            }catch (Exception e){
                logger.warn("param warn : 必填参数不全");
                result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "必填参数不全");

            }
        }

        return result;
    }

    @Override
    public Result getCarInfo(String userId) {
        Result result = Result.success();

        // 是否存在车辆
        TCar param = new TCar();
        param.setUserId(userId);
        param.setState(1);
        TCar temp = tUserCarMapper.selectOne(param);
        if (temp == null) {
            logger.warn("param warn : 车辆不存在");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "车辆不存在或待审核 ");
            return result;
        }
        // 返回
        result.setData(temp);
        return result;
    }
}
