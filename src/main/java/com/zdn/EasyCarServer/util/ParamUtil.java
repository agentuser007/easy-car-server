package com.zdn.EasyCarServer.util;

import com.zdn.EasyCarServer.model.PageInfo;
import com.zdn.EasyCarServer.vo.PageVO;

import java.util.List;
import java.util.UUID;

public class ParamUtil {
    /**
     * 分页参数为空
     */
    public static Boolean pageIsNull(PageVO pageVO) {
        return (pageVO.getPageNum() == null || pageVO.getPageSize() == null);
    }

    /**
     * 设置分页
     */
    public static void setPage(PageVO pageVO) {
        Integer pageNum = pageVO.getPageNum();
        Integer pageSize = pageVO.getPageSize();
        pageNum = pageNum == null ? 1 : pageNum;
        pageNum = pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        pageVO.setPageNum(pageNum);
        pageVO.setPageSize(pageSize);
    }

    public static <T> void setPageInfo(PageInfo<T> pageInfo, PageVO pageVO, Integer total, List<T> list) {
        pageInfo.setPageNum(pageVO.getPageNum());
        pageInfo.setPageSize(pageVO.getPageSize());
        pageInfo.setTotal(total);
        pageInfo.setList(list);
        pageInfo.setSize(list == null ? 0 : list.size());
    }

    /**
     * 获取uuid
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
