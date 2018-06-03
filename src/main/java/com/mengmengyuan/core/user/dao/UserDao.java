/**    
 * 文件名：UserDao.java    
 *    
 * 版本信息：    
 * 日期：2018年6月1日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mengmengyuan.core.base.BaseDao;
import com.mengmengyuan.core.user.entity.UserInfo;

/**
 * 
 * 项目名称：mengmengyuan 类名称：UserDao 类描述： 创建人：Administrator 创建时间：2018年6月1日 下午5:38:43
 * 修改人：Administrator 修改时间：2018年6月1日 下午5:38:43 修改备注：
 * 
 * @version
 * 
 */
@Mapper
public interface UserDao extends BaseDao<UserInfo> {

    /**
     * 
     * getByLoginNameAndPassword(根据用户登录名及密码（md5加密）获取制定用户)
     * 
     * 
     */
    public UserInfo getByLoginNameAndPassword(UserInfo userInfo);

    /**
     * 
     * updateImage(更新用户头像)
     * 
     * 
     */
    public int updateImage(UserInfo userInfo);

    /**
     * 
     * updateNickname(更新用户昵称)
     * 
     */
    public int updateNickname(UserInfo user);

    /**
     * 
     * updatePassword(更新用户密码)
     * 
     * 
     */
    public int updatePassword(UserInfo user);

}
