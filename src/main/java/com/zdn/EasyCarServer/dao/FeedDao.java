package com.zdn.EasyCarServer.dao;


import com.zdn.EasyCarServer.model.Feed;
import com.zdn.EasyCarServer.vo.FeedSearchVO;
import com.zdn.EasyCarServer.vo.IdVO;

import java.util.List;

public interface FeedDao {

    Integer feedTotal(FeedSearchVO feedSearchVO);

    List<Feed> pageFeed(FeedSearchVO feedSearchVO);

    void viewFeed(IdVO idVO);


}