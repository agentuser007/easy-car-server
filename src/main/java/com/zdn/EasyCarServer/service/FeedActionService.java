package com.zdn.EasyCarServer.service;


import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.vo.FeedActionVO;

/**
 * desc   : 动态相关操作，0点赞1收藏
 * version: 1.0
 */
public interface FeedActionService {

    Result saveFeedAction(FeedActionVO feedActionVO, String userId);

    Result removeFeedAction(FeedActionVO feedActionVO, String userId);
}
