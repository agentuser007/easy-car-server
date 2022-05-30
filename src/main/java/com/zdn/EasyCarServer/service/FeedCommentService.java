package com.zdn.EasyCarServer.service;

import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.vo.*;

/**
 * desc   : 动态评论
 * version: 1.0
 */
public interface FeedCommentService {

    Result pageFeedComment(FeedCommentVO feedCommentVO);

    Result saveFeedComment(FeedCommentSaveVO feedCommentSaveVO, String userId);

    Result unreadReply(String userId);

    Result updateUnreadReply(String userId);
}
