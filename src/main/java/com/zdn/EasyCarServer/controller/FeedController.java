package com.zdn.EasyCarServer.controller;

import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.service.FeedService;
import com.zdn.EasyCarServer.util.ParamUtil;
import com.zdn.EasyCarServer.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * 动态相关
 */
@Api(tags = "02-feed", value = "FeedApi", description = "动态相关接口")
@RestController
@RequestMapping("/feed")
public class FeedController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FeedService feedService;

    /**
     * 动态分页
     */
    @ApiOperation(value = "分页动态", notes = "分页动态接口")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public Result pageFeed(FeedSearchVO feedSearchVO
                           //, @RequestAttribute(name = "userId") String userId
                           //TODO：注意！测试时取消api拦截，联调app记得恢复！！

    ) {

        if (feedSearchVO == null) {
            return Result.paramIsNull();
        }

        if (ParamUtil.pageIsNull(feedSearchVO)) {
            return Result.pageIsNull();
        }

        logger.info("param is :" + feedSearchVO.toString());
        return feedService.pageFeed(feedSearchVO, feedSearchVO.getUserId());

        //return feedService.pageFeed(feedSearchVO, userId);
    }

    /**
     * 动态保存
     */
    @ApiOperation(value = "发布动态", notes = "发布动态接口--ok")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveFeed( FeedSaveVO feedSaveVO
            //, @ApiIgnore @RequestAttribute(name = "userId") String userId
                            //TODO：注意！测试时取消api拦截，联调app记得恢复！！
    ) {

        if (feedSaveVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + feedSaveVO.toString());
        return feedService.saveFeed(feedSaveVO, feedSaveVO.getUserId());

        //return feedService.saveFeed(feedSaveVO, userId);
    }



    /**
     * 动态删除
     */
    @ApiOperation(value = "删除动态", notes = "删除动态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="header", name = "X-App-Token", value = "token", required = true),
            @ApiImplicitParam(paramType="form", name = "id", value = "动态id", required = true)
    })
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Result removeFeed(IdVO idVO,
                             @ApiIgnore @RequestAttribute(name = "userId") String userId) {

        if (idVO == null) {
            return Result.paramIsNull();
        }

        if (StringUtils.isEmpty(idVO.getId())) {
            return Result.idIsNull();
        }

        logger.info("param is :" + idVO.toString());

        return feedService.removeFeed(idVO, userId);
    }

}