package com.zdn.EasyCarServer.service;

import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.vo.FeedSaveVO;
import com.zdn.EasyCarServer.vo.FeedSearchVO;
import com.zdn.EasyCarServer.vo.IdVO;

/**
 * desc   : 动态
 * version: 1.0
 */
public interface FeedService {

    Result pageFeed(FeedSearchVO feedSearchVO, String userId);

    Result saveFeed(FeedSaveVO feedSaveVO, String userId);

    Result viewFeed(IdVO idVO);

    Result removeFeed(IdVO idVO, String userId);

}