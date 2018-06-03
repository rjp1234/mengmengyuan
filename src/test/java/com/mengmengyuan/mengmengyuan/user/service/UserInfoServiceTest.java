/**    
 * 文件名：UserInfoServiceTest.java    
 *    
 * 版本信息：    
 * 日期：2018年6月2日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.mengmengyuan.user.service;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mengmengyuan.common.util.IdGen;
import com.mengmengyuan.common.util.MD5Utils;
import com.mengmengyuan.common.util.redis.RedisUtils;
import com.mengmengyuan.core.user.entity.UserInfo;
import com.mengmengyuan.core.user.service.UserInfoService;
import com.mengmengyuan.core.user.utils.UserUtils;
import com.mengmengyuan.mengmengyuan.BaseTest;

/**
 * 
 * 项目名称：mengmengyuan 类名称：UserInfoServiceTest 类描述： 创建人：Administrator
 * 创建时间：2018年6月2日 上午10:28:24 修改人：Administrator 修改时间：2018年6月2日 上午10:28:24 修改备注：
 * 
 * @version
 * 
 */
public class UserInfoServiceTest extends BaseTest {

    @Autowired
    UserInfoService service;

    @Autowired
    RedisUtils redisUtils;

    @Test
    public void registTest() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(IdGen.uuid());
        userInfo.setImage(
                "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKA5fGqs9PXeXCZM055lm8ibzhGSDueyntdibgD9ZsjgYba2HuMu8s5ODaibCCFAWoG5SyxhJQx1fOxA/132");
        userInfo.setPassword(MD5Utils.MD5("940213"));
        userInfo.setLoginName("renjianping");
        userInfo.setNickname("renjianping");
        userInfo.setLoginDate(new Date(System.currentTimeMillis()));
        userInfo.setUserType(UserInfo.USER_TYPE_LOCAL_STUDENT);
        // service.regist(userInfo);
    }

    @Test
    public void getUserByLogin() {
        String password = "940213";
        String loginname = "renjianpng";
        System.out.println(service.getUserByLogin(loginname, password));

    }

    @Test
    public void updateImage_test() throws Exception {
        String password = "940213";
        String loginname = "renjianping";
        UserInfo user = service.getUserByLogin(loginname, password);
        String originImage = user.getImage();
        service.updateImage(user.getId(), "test");
        String image = (String) redisUtils.hmGet(UserUtils.USER_HASH_PREFIX + user.getId(), UserUtils.USER_HASH_IMAGE);
        String image2 = service.getUserByLogin(loginname, password).getImage();
        service.updateImage(user.getId(), originImage);
        assertEquals(image2, image);
    }

    @Test
    public void updateNickName_test() throws Exception {
        String password = "940213";
        String loginname = "renjianping";
        UserInfo user = service.getUserByLogin(loginname, password);
        String originNick = user.getNickname();
        service.updateUserNick(user.getId(), "test");
        String nick1 = (String) redisUtils.hmGet(UserUtils.USER_HASH_PREFIX + user.getId(), UserUtils.USER_HASH_NICK);
        String nick2 = service.getUserByLogin(loginname, password).getNickname();
        service.updateUserNick(user.getId(), originNick);
        assertEquals(nick2, nick1);
    }
}
