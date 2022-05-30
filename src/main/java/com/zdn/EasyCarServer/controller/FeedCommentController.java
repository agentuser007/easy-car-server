package com.zdn.EasyCarServer.controller;

import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.service.FeedCommentService;
import com.zdn.EasyCarServer.util.ParamUtil;
import com.zdn.EasyCarServer.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**

 * desc   : 动态评论
 * version: 1.0
 */
@Api(tags = "04-feed-comment", value = "FeedCommentApi", description = "动态评论接口")
@RestController
@RequestMapping("/feed/comment")
public class FeedCommentController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FeedCommentService feedCommentService;

    @ApiOperation(value = "分页动态评论", notes = "分页动态评论接口")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public Result pageFeedEvaluate(FeedCommentVO feedCommentVO) {

        if (feedCommentVO == null) {
            return Result.paramIsNull();
        }

        if (ParamUtil.pageIsNull(feedCommentVO)) {
            return Result.pageIsNull();
        }

        logger.info("param is :" + feedCommentVO.toString());

        return feedCommentService.pageFeedComment(feedCommentVO);
    }


    @ApiOperation(value = "动态评论", notes = "动态评论接口--ok")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveFeedComment( FeedCommentSaveVO feedCommentSaveVO
           // , @RequestAttribute(name = "userId") String userId
                                  // TODO：注意！测试时取消api拦截，联调app记得恢复！！

    ) {

        if (feedCommentSaveVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + feedCommentSaveVO.toString());
        return feedCommentService.saveFeedComment(feedCommentSaveVO, feedCommentSaveVO.getUserId());

       // return feedCommentService.saveFeedComment(feedCommentSaveVO, userId);
    }

    @ApiOperation(value = "未读评论数", notes = "未读评论数接口接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true)
    })
    @RequestMapping(value = "/unread", method = RequestMethod.POST)
    @ResponseBody
    public Result unreadReply(@ApiIgnore @RequestAttribute(name = "userId") String userId) {

        logger.info("param is :" + userId);

        return feedCommentService.unreadReply(userId);
    }

    @ApiOperation(value = "未读评论已读", notes = "未读已读接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true)
    })
    @RequestMapping(value = "/unread/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUnreadReply(@ApiIgnore @RequestAttribute(name = "userId") String userId) {

        logger.info("param is :" + userId);

        return feedCommentService.updateUnreadReply(userId);
    }
}
