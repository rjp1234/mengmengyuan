/**    
 * 文件名：UserDetailService.java    
 *    
 * 版本信息：    
 * 日期：2018年7月8日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mengmengyuan.core.user.dao.UserDetailDao;
import com.mengmengyuan.core.user.entity.UserRankDetailPageInfo;

/**
 * 
 * 项目名称：mengmengyuan 类名称：UserDetailService 类描述： 创建人：Administrator 创建时间：2018年7月8日
 * 上午10:59:07 修改人：Administrator 修改时间：2018年7月8日 上午10:59:07 修改备注：
 * 
 * @version学生学习信息操作service
 * 
 */
@Service
public class UserDetailService {

    @Autowired
    UserDetailDao userDetailDao;

    public UserRankDetailPageInfo getUserRankDetailPageInfoByUserId(String userId) {
        UserRankDetailPageInfo user = new UserRankDetailPageInfo();
        user.setId(userId);
        return userDetailDao.getUserRankDetailPageInfoByUserId(user);
    }
}
