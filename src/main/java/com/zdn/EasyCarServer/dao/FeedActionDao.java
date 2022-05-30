package com.zdn.EasyCarServer.dao;


import com.zdn.EasyCarServer.entity.TFeedAction;

import java.util.List;

/**
 * desc   : 动态操作，点赞
 */
public interface FeedActionDao {

    List<TFeedAction> listAll();
}
