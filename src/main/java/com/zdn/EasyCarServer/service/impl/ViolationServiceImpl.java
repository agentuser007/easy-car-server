package com.zdn.EasyCarServer.service.impl;

import com.zdn.EasyCarServer.constant.Constant;
import com.zdn.EasyCarServer.entity.TViolation;
import com.zdn.EasyCarServer.mapper.TViolationMapper;
import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.service.ViolationService;
import com.zdn.EasyCarServer.util.ParamUtil;
import com.zdn.EasyCarServer.vo.ViolationVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ViolationServiceImpl implements ViolationService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TViolationMapper tViolationMapper;

    @Override
    public Result setIsLookViolation(String vioId) {
        Result result = Result.success();

        TViolation param = new TViolation();
        param.setVioId(vioId);
        TViolation tViolation = tViolationMapper.selectOne(param);
        if(tViolation == null){
            logger.warn("param warn : 参数错误");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "参数错误");
            return result;
        }
        tViolation.setIsLook("1");
        tViolationMapper.updateByPrimaryKeySelective(tViolation);
        result.setData(tViolation);
        return result;
    }

    @Override
    public Result getUnLookViolation(String licence) {
        Result result = Result.success();

        TViolation param = new TViolation();
        param.setLicence(licence);
        param.setIsLook("0");
        List<TViolation> temp = tViolationMapper.select(param);

        if (temp == null) {
            logger.warn("param warn : 车辆暂无违章");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "车辆暂无违章");
            return result;
        }
        // 返回
        result.setData(temp);
        return result;
    }

    @Override
    public Result getViolationList(String licence) {
        Result result = Result.success();

        TViolation param = new TViolation();
        param.setLicence(licence);
        List<TViolation> temp = tViolationMapper.select(param);

        if (temp == null) {
            logger.warn("param warn : 车辆暂无违章");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "车辆暂无违章");
            return result;
        }
        // 按创建违章时间排序
        Collections.sort(temp, new Comparator<TViolation>(){
            @Override
            public int compare(TViolation o1, TViolation o2) {
                return o2.getCreateTime().compareTo(o1.getCreateTime());
            }
        });
        // 返回
        result.setData(temp);
        return result;
    }

    @Override
    public Result insertNewViolation(ViolationVO violationVO) {
        Result result = Result.success();
        // 参数校验
        String licence = violationVO.getLicence();
        if (StringUtils.isEmpty(licence)) {
            logger.warn("param warn : 必填参数不全");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "必填参数不全");
            return result;
        }

        // 数据转换
        TViolation tViolation = new TViolation();
        BeanUtils.copyProperties(violationVO, tViolation);
        String id = ParamUtil.getUUID();
        tViolation.setVioId(id);
        tViolationMapper.insertSelective(tViolation);

        result.setData(tViolationMapper.selectByPrimaryKey(id));
        return result;    }
}
