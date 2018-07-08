/**    
 * 文件名：UserDetailDao.java    
 *    
 * 版本信息：    
 * 日期：2018年7月8日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mengmengyuan.core.user.entity.UserRankDetailPageInfo;

/**
 * 
 * 项目名称：mengmengyuan 类名称：UserDetailDao 类描述： 创建人：Administrator 创建时间：2018年7月8日
 * 上午10:59:18 修改人：Administrator 修改时间：2018年7月8日 上午10:59:18 修改备注：
 * 
 * @version学生学习信息查询
 * 
 */
@Mapper
public interface UserDetailDao {

    /**
     * 
     * getUserRankDetailPageInfoByUserId(根据学生id查询学生当前名次详情信息)
     * 
     */
    public UserRankDetailPageInfo getUserRankDetailPageInfoByUserId(UserRankDetailPageInfo userRankDetailPageInfo);

}
