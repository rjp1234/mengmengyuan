/**    
 * 文件名：UserInfoService.java    
 *    
 * 版本信息：    
 * 日期：2018年6月1日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.user.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mengmengyuan.common.util.MD5Utils;
import com.mengmengyuan.common.util.TimeUtils;
import com.mengmengyuan.common.util.redis.RedisUtils;
import com.mengmengyuan.core.user.dao.UserDao;
import com.mengmengyuan.core.user.entity.UserInfo;
import com.mengmengyuan.core.user.utils.UserUtils;

/**
 * 
 * 项目名称：mengmengyuan 类名称：UserInfoSe
 * 
 * import org.springframework.stereotype.Service;rvice 类描述： 创建人：Administrator
 * 创建时间：2018年6月1日 下午5:43:06 修改人：Administrator 修改时间：2018年6月1日 下午5:43:06 修改备注：
 * 
 * @version
 * 
 */
@Service
public class UserInfoService {
    @Autowired
    UserDao userDao;

    @Autowired
    RedisUtils redisUtils;

    public boolean regist(UserInfo userInfo) throws Exception {
        int i = userDao.insert(userInfo);
        // 根据手机号\登录名\密码 生成acctoken
        String acctoken = UserUtils.getUserAccToken(userInfo);
        if (i == 1) {
            redisUtils.lPush(userInfo.getId(), acctoken);
            return true;
        }
        return false;
    }

    /**
     * 
     * getUserByLogin(根据登录名和密码查询用户是否存在)
     * 
     * 
     */
    public UserInfo getUserByLogin(String loginname, String password) {
        // TODO Auto-generated method stub
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginName(loginname);
        // 密码进行md5加密
        password = MD5Utils.MD5(password);
        userInfo.setPassword(password);
        return userDao.getByLoginNameAndPassword(userInfo);
    }

    /**
     * 变更用户头像
     */
    public int updateImage(String userId, String image) throws Exception {
        UserInfo user = new UserInfo();
        user.setId(userId);
        user.setImage(image);
        redisUtils.hmSet(UserUtils.USER_HASH_PREFIX + userId, "image", image);
        return userDao.updateImage(user);
    }

    /**
     * 变更用户头像
     */
    public int updateUserNick(String userId, String nickname) throws Exception {
        UserInfo user = new UserInfo();
        user.setId(userId);
        user.setNickname(nickname);
        redisUtils.hmSet(UserUtils.USER_HASH_PREFIX + userId, "nickname", nickname);
        return userDao.updateNickname(user);
    }

    /**
     * 用户生成acctoken
     */
    public String updateAccToken(UserInfo userInfo) {
        String accToken = UserUtils.getUserAccToken(userInfo);
        redisUtils.hmSet(UserUtils.USER_HASH_PREFIX + userInfo.getId(), UserUtils.USER_HASH_ACCTOKEN,
                accToken + "-" + TimeUtils.formatNowDay());
        return accToken;
    }

    /**
     * 
     * checkAccToken(这里用一句话描述这个方法的作用)
     * 
     * 
     */
    public boolean checkAccToken(String userId, String accToken) {
        String accTokenWithTime = (String) redisUtils.hmGet(UserUtils.USER_HASH_PREFIX + userId,
                UserUtils.USER_HASH_ACCTOKEN);
        return (accToken + TimeUtils.formatNowDay()).equals(accTokenWithTime);
    }

    /**
     * 
     * getUserLastLoginTime(检验用户accToken是否有效)
     * 
     * @return 可能为null
     */
    public Date getUserLastLoginTime(String userId) {
        if (!redisUtils.hmHasKey(UserUtils.USER_HASH_PREFIX + userId, UserUtils.USER_HASH_LAST_LOGINTIME)) {
            updateLoginTime(userId);
            return null;
        }

        long time = (long) redisUtils.hmGet(UserUtils.USER_HASH_PREFIX + userId, UserUtils.USER_HASH_LAST_LOGINTIME);
        return new Date(time);
    }

    /**
     * 
     * setLoginTime(设置最后一次登录时间)
     * 
     * 
     */
    public void updateLoginTime(String userId) {
        redisUtils.hmSet(UserUtils.USER_HASH_PREFIX + userId, UserUtils.USER_HASH_LAST_LOGINTIME,
                System.currentTimeMillis());
    }

    /**
     * 
     * changePassword(这里用一句话描述这个方法的作用)
     * 
     * 
     * 
     */
    public int changePassword(String userId, String password4Change) {
        UserInfo user = new UserInfo();
        user.setId(userId);
        user.setPassword(MD5Utils.MD5(password4Change));
        int i = userDao.updatePassword(user);
        if (i != 1) {
            throw new RuntimeException("change password error:userId=" + userId + "&password=" + password4Change);
        }
        return i;

    }

}
