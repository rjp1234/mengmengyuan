/**    
 * 文件名：UserLoginController.java    
 *    
 * 版本信息：    
 * 日期：2018年6月1日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mengmengyuan.common.ReturnConstants;
import com.mengmengyuan.common.ThreadPool;
import com.mengmengyuan.common.util.TimeUtils;
import com.mengmengyuan.core.base.ApiResponse;
import com.mengmengyuan.core.base.BaseController;
import com.mengmengyuan.core.user.entity.UserInfo;
import com.mengmengyuan.core.user.service.UserInfoService;

/**
 * 
 * 项目名称：mengmengyuan 类名称：UserLoginController 类描述：用户登录、注册接口 创建人：Administrator
 * 创建时间：2018年6月1日 下午4:56:16 修改人：Administrator 修改时间：2018年6月1日 下午4:56:16 修改备注：
 * 
 * @version
 * 
 */
@RestController
@RequestMapping(path = "user", method = { RequestMethod.POST })
public class UserLoginController extends BaseController {
    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("regist")
    public ApiResponse regist(UserInfo userInfo) {
        return null;
    }

    @RequestMapping("login")
    public ApiResponse login(HttpServletRequest request, HttpServletResponse response) {
        String loginname = request.getParameter("loginname");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String image = request.getParameter("image");

        /**
         * 登录名及密码不得为空，为空返回对应错误码
         */
        if (StringUtils.isBlank(loginname)) {
            return ApiResponse.failMessage(ReturnConstants.ERROR_LOGIN_NAME_EMPTY, "login name can't be empty");
        }
        if (StringUtils.isBlank(password)) {
            return ApiResponse.failMessage(ReturnConstants.ERROR_PASSWORD_EMPTY, "password can't be empty");
        }
        UserInfo userInfo = userInfoService.getUserByLogin(loginname, password);

        if (userInfo == null) {
            return ApiResponse.failMessage(ReturnConstants.ERROR_PASSWORD_WORNG, "loginname or password is wrong");
        }
        /**
         * 用户昵称及头像比对，若不为空且不同与本地，则将新的数据同步至数据库中
         */
        if (StringUtils.isNotBlank(nickname) && !nickname.equals(userInfo.getNickname())) {
            ThreadPool.getInstance().execute(() -> {
                try {
                    userInfoService.updateUserNick(userInfo.getId(), nickname);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            });
        }

        if (StringUtils.isNotBlank(image) && !image.equals(userInfo.getImage())) {
            ThreadPool.getInstance().execute(() -> {
                try {
                    userInfoService.updateImage(userInfo.getId(), image);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            });

        }

        // 生成用户accToken
        String accToken = userInfoService.updateAccToken(userInfo);
        String userId = userInfo.getId();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("accToken", accToken);
        returnMap.put("userId", userId);
        // 获取用户上一次登录的时间
        Date userLastLoginTime = userInfoService.getUserLastLoginTime(userId);
        returnMap.put("lastLoginTime", TimeUtils.formateDate(userLastLoginTime));
        returnMap.put("phonenum", userInfo.getPhonenum());
        // 更新登录时间
        userInfoService.updateLoginTime(userId);
        return ApiResponse.successMessage(returnMap);

    }

}
