package com.zdn.EasyCarServer.service.impl;

import com.zdn.EasyCarServer.constant.Constant;
import com.zdn.EasyCarServer.dao.FeedCommentDao;
import com.zdn.EasyCarServer.entity.TFeedComment;
import com.zdn.EasyCarServer.mapper.TFeedCommentMapper;
import com.zdn.EasyCarServer.model.Comment;
import com.zdn.EasyCarServer.model.PageInfo;
import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.service.FeedCommentService;
import com.zdn.EasyCarServer.util.ParamUtil;
import com.zdn.EasyCarServer.vo.FeedCommentSaveVO;
import com.zdn.EasyCarServer.vo.FeedCommentVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * desc   : 动态评论
 * version: 1.0
 */
@Service
public class FeedCommentServiceImpl implements FeedCommentService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TFeedCommentMapper tFeedCommentMapper;

    @Resource
    private FeedCommentDao feedCommentDao;

    @Override
    public Result pageFeedComment(FeedCommentVO feedCommentVO) {
        Result result = Result.success();

        String feedId = feedCommentVO.getFeedId();
        if (StringUtils.isEmpty(feedId)) {
            logger.warn("param warn : feedId为空");
            result.setCodeAndMsg(Constant.ERROR_CODE_ID_NULL, "feedId为空");
            return result;
        }
        // 设置分页
        ParamUtil.setPage(feedCommentVO);
        Integer total = feedCommentDao.commentTotal();
        List<Comment> commentList = feedCommentDao.pageFeedComment(feedCommentVO);
        // 分页数据
        PageInfo<Comment> pageInfo = new PageInfo<>();
        ParamUtil.setPageInfo(pageInfo, feedCommentVO, total, commentList);

        result.setData(pageInfo);
        return result;
    }

    /**
     * 保存动态评论
     */
    @Override
    public Result saveFeedComment(FeedCommentSaveVO feedCommentSaveVO, String userId) {
        Result result = Result.success();
        // 参数校验
        String feedId = feedCommentSaveVO.getFeedId();
        String commentInfo = feedCommentSaveVO.getCommentInfo();
        if (StringUtils.isEmpty(feedId) || StringUtils.isEmpty(commentInfo)) {
            logger.warn("param warn : 必填参数不全");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "必填参数不全");
            return result;
        }

        // 数据转换
        TFeedComment tFeedComment = new TFeedComment();
        BeanUtils.copyProperties(feedCommentSaveVO, tFeedComment);
        String id = ParamUtil.getUUID();
        tFeedComment.setId(id);
        tFeedComment.setUserId(userId);
        tFeedCommentMapper.insertSelective(tFeedComment);

        result.setData(tFeedCommentMapper.selectByPrimaryKey(id));
        return result;
    }

    @Override
    public Result unreadReply(String userId) {
        Result result = Result.success();

        Example example = new Example(TFeedComment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("toUserId", userId);
        criteria.andEqualTo("isLook", false);

        Integer count = tFeedCommentMapper.selectCountByExample(example);

        result.setData(count);
        return result;
    }

    @Override
    public Result updateUnreadReply(String userId) {
        Result result = Result.success();

        TFeedComment param = new TFeedComment();
        param.setIsLook(true);

        Example example = new Example(TFeedComment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isLook", false);

        Integer state = tFeedCommentMapper.updateByExampleSelective(param, example);

        result.setData(state);
        return result;
    }
}
