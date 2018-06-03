/**    
 * 文件名：UserUtils.java    
 *    
 * 版本信息：    
 * 日期：2018年6月1日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.user.utils;

import java.util.concurrent.TimeUnit;

import com.mengmengyuan.common.config.Global;
import com.mengmengyuan.common.util.MD5Utils;
import com.mengmengyuan.core.user.entity.UserInfo;

/**
 * 
 * 项目名称：mengmengyuan 类名称：UserUtils 类描述： 创建人：Administrator 创建时间：2018年6月1日
 * 下午9:44:30 修改人：Administrator 修改时间：2018年6月1日 下午9:44:30 修改备注：
 * 
 * @version
 * 
 */
public class UserUtils {
    // 用户登录信息（包含用户昵称、用户头像、用户acctoken）失效时间 单位s
    public static final int USER_MSG_FAILURE_TIME = Integer.parseInt(Global.getConfig("USER_MSG_FAILURE_TIME"));

    // redis存储中userhash的前缀 存储结构为key=user:userId hashkey="参数名" value ="参数"
    public static final String USER_HASH_PREFIX = "user:";

    // 用户hash存储的头像
    public static final String USER_HASH_IMAGE = "image";

    // 用户hash存储的昵称
    public static final String USER_HASH_NICK = "nickname";

    // 用户hash存储的accToken
    public static final String USER_HASH_ACCTOKEN = "acctoken";

    // 用户hash存储的上次登录时间
    public static final String USER_HASH_LAST_LOGINTIME = "logintime";

    /**
     * 
     * getUserAccToken(根据用户手机号+"-"+登录名+"-"+用户密码生成对应的accToken)
     * 
     * 
     */
    public static String getUserAccToken(UserInfo userInfo) {
        String accToken = MD5Utils
                .MD5(userInfo.getPhonenum() + "-" + userInfo.getLoginname() + "-" + userInfo.getPassword());
        userInfo.setAccToken(accToken);
        return accToken;
    }

    public static void main(String[] args) {
        System.out.println(TimeUnit.DAYS.toSeconds(1));
    }

}
