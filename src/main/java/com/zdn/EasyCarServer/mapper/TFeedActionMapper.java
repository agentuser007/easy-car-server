package com.zdn.EasyCarServer.mapper;

import com.zdn.EasyCarServer.entity.TFeedAction;
import com.zdn.EasyCarServer.mybatis.BaseMapper;

public interface TFeedActionMapper extends BaseMapper<TFeedAction> {

    /**
     * 批量插入，Oralce需要设置useGeneratedKeys=false，不然报错
     *  Oracle批量插入：  insert all into table(...) values(...) into table(...) values(...) select * from dual
     *  Mysql批量插入：   insert into table(...) values(...),(...)
     * @param accounts
     * @return
     */
}