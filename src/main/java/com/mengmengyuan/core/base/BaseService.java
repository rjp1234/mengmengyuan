/**    
 * 文件名：BaseService.java    
 *    
 * 版本信息：    
 * 日期：2018年6月14日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mengmengyuan.common.util.redis.RedisUtils;

/**
 * 
 * 项目名称：mengmengyuan 类名称：BaseService 类描述： 创建人：Administrator 创建时间：2018年6月14日
 * 下午2:44:56 修改人：Administrator 修改时间：2018年6月14日 下午2:44:56 修改备注：
 * 
 * @version
 * 
 */
public class BaseService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RedisUtils redisUtils;
}
