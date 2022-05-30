package com.zdn.EasyCarServer.controller;

import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.service.FeedActionService;
import com.zdn.EasyCarServer.vo.FeedActionVO;
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
 * desc   : 动态相关操作
 * version: 1.0
 */
@Api(tags = "03-feed-action", value = "FeedActionApi", description = "动态相关操作接口")
@RestController
@RequestMapping("/feed/action")
public class FeedActionController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FeedActionService feedActionService;

    @ApiOperation(value = "动态操作", notes = "动态喜欢接口--ok")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveFeedAction( FeedActionVO feedActionVO
           // , @ApiIgnore @RequestAttribute(name = "userId") String userId
    ) {

        if (feedActionVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + feedActionVO.toString());

        return feedActionService.saveFeedAction(feedActionVO, feedActionVO.getUserId());
        //TODO：注意！测试时取消api拦截，联调app记得恢复！！
      //  return feedActionService.saveFeedAction(feedActionVO, userId);
    }

    //@ApiIgnore
    @ApiOperation(value = "动态操作", notes = "移除动态喜欢接口")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public Result removeFeedAction( FeedActionVO feedActionVO
           // , @RequestAttribute(name = "userId") String userId
    ) {

        if (feedActionVO == null) {
            return Result.paramIsNull();
        }

        logger.info("param is :" + feedActionVO.toString());
        //TODO：注意！测试时取消api拦截，联调app记得恢复！！
        return feedActionService.removeFeedAction(feedActionVO, feedActionVO.getUserId());

        //return feedActionService.removeFeedAction(feedActionVO, userId);
    }
}
