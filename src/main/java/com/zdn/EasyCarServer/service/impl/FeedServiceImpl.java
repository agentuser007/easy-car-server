package com.zdn.EasyCarServer.service.impl;

import com.zdn.EasyCarServer.constant.Constant;
import com.zdn.EasyCarServer.constant.RssConfig;
import com.zdn.EasyCarServer.dao.FeedDao;
import com.zdn.EasyCarServer.entity.TFeed;
import com.zdn.EasyCarServer.mapper.TFeedCommentMapper;
import com.zdn.EasyCarServer.mapper.TFeedMapper;
import com.zdn.EasyCarServer.mapper.TUserMapper;
import com.zdn.EasyCarServer.model.Feed;
import com.zdn.EasyCarServer.model.PageInfo;
import com.zdn.EasyCarServer.result.Result;
import com.zdn.EasyCarServer.service.FeedService;
import com.zdn.EasyCarServer.util.ParamUtil;
import com.zdn.EasyCarServer.vo.FeedSaveVO;
import com.zdn.EasyCarServer.vo.FeedSearchVO;
import com.zdn.EasyCarServer.vo.IdVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * desc   : 动态
 */
@Service
public class FeedServiceImpl implements FeedService {

    @Resource
    private RssConfig rssConfig;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TFeedMapper tFeedMapper;

    @Resource
    private FeedDao feedDao;


    @Resource
    private TFeedCommentMapper tFeedCommentMapper;

    @Resource
    private TUserMapper tUserMapper;

    /**
     * 查找动态，如有用户id查找对应动态
     */
    @Override
    public Result pageFeed(FeedSearchVO feedSearchVO, String userId) {
        Result result = Result.success();

        ParamUtil.setPage(feedSearchVO);

        feedSearchVO.setUserId(userId);
        Integer total = feedDao.feedTotal(feedSearchVO);
        List<Feed> feedList = feedDao.pageFeed(feedSearchVO);

        // 分页数据
        PageInfo<Feed> pageInfo = new PageInfo<>();

        ParamUtil.setPageInfo(pageInfo, feedSearchVO, total, feedList);

        result.setData(pageInfo);
        return result;
    }

    /**
     * 保存用户动态
     *
     * @param feedSaveVO 用户动态
     * @param userId     用户id
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Override
    public Result saveFeed(FeedSaveVO feedSaveVO, String userId) {
        Result result = Result.success();
        logger.warn("param warn : feed: "+feedSaveVO.toString());

        String feedInfo = feedSaveVO.getFeedInfo();
        String feedTitle = feedSaveVO.getFeedTitle();
        if (feedInfo.isEmpty() || feedTitle.isEmpty()) {
            logger.warn("param warn : feedInfo为空");
            result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, Constant.ERROR_MSG_PARAM_NULL);
            return result;
        }

        // 保存动态
        String feedId = ParamUtil.getUUID();
        TFeed tFeed = new TFeed();
        tFeed.setFeedId(feedId);
        tFeed.setUserId(userId);
        tFeed.setFeedInfo(feedInfo);
        tFeed.setFeedTitle(feedTitle);
        tFeed.setState(feedSaveVO.getState().substring(0,1));

        // 保存图片--只保存一张
        MultipartFile[] files = feedSaveVO.getPhoto();
        if(files != null){
            String[] types = new String[]{".jpg", ".jpeg", ".webp", ".png", ".gif"};
            String url = uploadFile(files, rssConfig.getUploadPath(), types);
            if (url.isEmpty()){
                result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "上传失败" );
                return result;
            }
            tFeed.setUrl(url);
            logger.info("photo {}", url);
        }

        tFeedMapper.insertSelective(tFeed);

        result.setData(tFeedMapper.selectByPrimaryKey(feedId));
        return result;
    }

    /**
     * 上传文件到本地
     */
    private String uploadFile(MultipartFile[] files, String typePath, String... types) {
        Result result = Result.success();

        // 此处为返回给前端的访问链接
        // 如果通过nginx访问静态资源，可以给域名或ip的拼接路径，或者给相对路径
        // 如：http://localhost:7070/rss/image/20180516001.png，此为完整访问路径
        // 或：/rss/image/20180516001.png，此为相对路径
        // 采用相对路径即typePath
        List<String> urls = new ArrayList<>();

        // 上传目录 | TODO:以年月划分的文件夹，减轻一个文件夹保存文件的压力
        //typePath = typePath + DateUtil.formatYm(new Date()) + RssConfig.FORWARD_SLASH;
        //typePath = typePath + RssConfig.FORWARD_SLASH;

        String dirPath = typePath;
        logger.warn("upload path : " + dirPath);


        for (MultipartFile multipartFile : files) {
            String fileName = multipartFile.getOriginalFilename();

            String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());

            if (!verifyType(fileType, types)) {
                logger.warn("uploadFile : " + fileName);
                //result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "格式不符合要求，只支持" + Arrays.toString(types));
                return "";
            }
            File file = new File(dirPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            // 重命名文件
            fileName = ParamUtil.getUUID() + fileType;
            file = new File(dirPath + fileName);

            try {
                // 保存文件
                multipartFile.transferTo(file);
            } catch (IOException e) {
                logger.warn("uploadFile : " + e.toString(), e);
                //result.setCodeAndMsg(Constant.ERROR_CODE_PARAM_NULL, "上传失败，error：" + e.toString());
                return "";
            }

            // url
            //String url = typePath + fileName;
            String url = "/image/" + fileName;

            urls.add(url);
        }

        return urls.get(0);
    }

    /**
     * 验证文件类型
     */
    private boolean verifyType(String type, String... types) {
        for (String str : types) {
            if (str.equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Result viewFeed(IdVO idVO) {
        feedDao.viewFeed(idVO);
        return Result.success();
    }



    /**
     * 删除动态
     * @param idVO 参数
     * @param userId 用户id
     */
    @Override
    public Result removeFeed(IdVO idVO, String userId) {
        String feedId = idVO.getId();
        TFeed tFeed = tFeedMapper.selectByPrimaryKey(feedId);
        if (tFeed == null) {
            return Result.objIsNull("动态不存在");
        }
        String state = tFeed.getState();
        if ("2".equals(state)) {
            return Result.objIsNull("动态不存在或已删除");
        }
        String feedUserId = tFeed.getUserId();
        if (!userId.equals(feedUserId)) {
            return Result.error("无法操作他人动态");
        }

        tFeed.setState("2");
        tFeedMapper.updateByPrimaryKeySelective(tFeed);
        return Result.success();
    }


}