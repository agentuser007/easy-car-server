package com.zdn.EasyCarServer.service.impl;

import com.zdn.EasyCarServer.constant.Constant;
import com.zdn.EasyCarServer.entity.TFeedAction;
import com.zdn.EasyCarServer.mapper.TFeedActionMapper;
import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.service.FeedActionService;
import com.zdn.EasyCarServer.util.ParamUtil;
import com.zdn.EasyCarServer.vo.FeedActionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * desc   : 动态点赞
 * version: 1.0
 */
@Service
public class FeedActionServiceImpl implements FeedActionService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TFeedActionMapper tFeedActionMapper;

    @Override
    public Result saveFeedAction(FeedActionVO feedActionVO, String userId) {
        Result result = Result.success();
        // 参数验证
        String feedId = feedActionVO.getFeedId();
        if (StringUtils.isEmpty(feedId)) {
            logger.warn("param warn : 必填参数不全");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "必填参数不全");
            return result;
        }
        // 是否已操作
        TFeedAction param = new TFeedAction();
        param.setFeedId(feedId);
        param.setUserId(userId);
        TFeedAction temp = tFeedActionMapper.selectOne(param);
        if (temp == null) {
            String id = ParamUtil.getUUID();
            param.setId(id);
            tFeedActionMapper.insertSelective(param);
            temp = tFeedActionMapper.selectByPrimaryKey(id);
            // 返回
            result.setData(temp);
        }else {
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "已经赞过了");
        }
        return result;
    }

    @Override
    public Result removeFeedAction(FeedActionVO feedActionVO, String userId) {
        Result result = Result.success();

        // 参数验证
        String feedId = feedActionVO.getFeedId();
        if (StringUtils.isEmpty(feedId)) {
            logger.warn("param warn : 必填参数不全");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "必填参数不全");
            return result;
        }

        TFeedAction param = new TFeedAction();
        param.setFeedId(feedId);
        param.setUserId(userId);
        tFeedActionMapper.delete(param);
        return result;
    }
}
