package com.zdn.EasyCarServer.dao;


import com.zdn.EasyCarServer.model.Comment;
import com.zdn.EasyCarServer.vo.FeedCommentVO;

import java.util.List;

/**
 * desc   : 动态评论
 * version: 1.0
 */
public interface FeedCommentDao {

    Integer commentTotal();

    List<Comment> pageFeedComment(FeedCommentVO feedCommentVO);
}
