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
import com.mengmengyuan.core.studio.entity.StudioInfo;
import com.mengmengyuan.core.studio.service.StudioInfoService;
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
public class UserController extends BaseController {
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    StudioInfoService studioInfoService;

    @RequestMapping("regist")
    public ApiResponse regist(UserInfo userInfo) {
        return null;
    }

    /**
     * 
     * login(用户登录接口)
     * 
     * 
     */
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
        returnMap.put("lastLoginTime", userLastLoginTime == null ? null : TimeUtils.formateDate(userLastLoginTime));
        returnMap.put("phonenum", userInfo.getPhonenum());
        // 更新登录时间
        userInfoService.updateLoginTime(userId);
        return ApiResponse.successMessage(returnMap);

    }

    /**
     * 
     * changePassword(用户更改密码)
     * 
     * 
     */
    @RequestMapping("changePassword")
    public ApiResponse changePassword(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String accToken = request.getParameter("accToken");
        String password4Change = request.getParameter("password");// 需要修改的密码
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(accToken) || StringUtils.isBlank(password4Change)) {
            return ApiResponse.failMessage(ReturnConstants.ERROR_PARAM_INVALID, "param is invalid");

        }
        if (password4Change.length() < 6) {
            return ApiResponse.failMessage(ReturnConstants.ERROR_PASSWORD_INVALID, "password is invalid");

        }

        boolean flag = userInfoService.checkAccToken(userId, accToken);
        if (!flag) {
            return ApiResponse.failMessage(ReturnConstants.ERROR_ACCTOKEN_INVALID, "accToken is invalid");
        }
        try {
            userInfoService.changePassword(userId, password4Change);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ApiResponse.failMessage("change password faild");
        }

        return ApiResponse.successMessage("change password success");

    }

    /**
     * 
     * userPage 首页显示分两块：个人信息及课文列表 .此处显示个人信息包含 1、总朗读篇数 2、总背诵篇数 3、教师评分总数 4、教师平均打分
     * 5、用户昵称(微信提供) 6、用户头像(微信提供)
     * 
     * 
     */
    @RequestMapping("userPage")
    public ApiResponse userPage(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        int pointTime = studioInfoService.countPointTime(userId);// 总计被打分次数
        int readTime = studioInfoService.countStudio(userId, null, StudioInfo.TYPE_READ);// 总朗读篇数
        int reciteTime = studioInfoService.countStudio(userId, null, StudioInfo.TYPE_RECITE);// 总背诵篇数
        int sumPoint = studioInfoService.getSumPointByUserId(userId);// 教师评分总数
        int avgPoint = sumPoint / pointTime;// 平均分
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("readTime", readTime);
        data.put("reciteTime", reciteTime);
        data.put("sumPoint", sumPoint);
        data.put("avgPoint", avgPoint);
        return ApiResponse.successMessage(data);
    }

}
