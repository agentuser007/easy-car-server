package com.zdn.EasyCarServer.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * desc   : 资源服务相关
 */
@Component
public class RssConfig {

    public static final String FORWARD_SLASH = "/";
    private final Logger logger = LoggerFactory.getLogger(getClass());



    /**
     * windows文件上传目录
     */
    @Value("${rss-path.windowsPath}")
    private String windowsPath;

    /**
     * mac文件上传目录
     */
    @Value("${rss-path.linuxPath}")
    private String linuxPath;

    /**
     * 获取文件上传目录
     */
    public String getUploadPath() {
        if (isWindows()) {
            return windowsPath + getServerPath();
        }
        return "/Users/dzzzg8/Desktop/easycar/";
    }

    /**
     * 获取服务目录
     */
    private String getServerPath() {
        return "/esaycar";
    }

    /**
     * 用户图片目录
     */
    public String getUserPath() {
        return "/user/";
    }

    /**
     * 动态图片目录
     */
    public String getFeedPath() {
        return "/feed/";
    }


    /**
     * 当前系统是否为windows
     */
    private boolean isWindows() {
        String osName = getOsName();
        return osName.toLowerCase().contains("windows");
    }

    /**
     * 获取系统名称
     */
    private String getOsName() {
        logger.info("param is :" + System.getProperties().getProperty("os.name"));

        return System.getProperties().getProperty("os.name");
    }

}
