/**    
 * 文件名：UserDetailController.java    
 *    
 * 版本信息：    
 * 日期：2018年7月8日    
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

import com.mengmengyuan.common.util.TimeUtils;
import com.mengmengyuan.core.base.ApiResponse;
import com.mengmengyuan.core.base.BaseController;
import com.mengmengyuan.core.user.entity.UserRankDetailPageInfo;
import com.mengmengyuan.core.user.service.UserDetailService;

/**
 * 
 * 项目名称：mengmengyuan 类名称：UserDetailController 类描述： 创建人：Administrator
 * 创建时间：2018年7月8日 上午9:44:50 修改人：Administrator 修改时间：2018年7月8日 上午9:44:50 修改备注：
 * 
 * @version
 * 
 */
@RestController
@RequestMapping(path = "user", method = { RequestMethod.POST })
public class UserDetailController extends BaseController {

    @Autowired
    UserDetailService userDetailService;

    @RequestMapping("userDetail")
    public ApiResponse userDetail(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        Map<String, Object> data = new HashMap<String, Object>();
        try {
            UserRankDetailPageInfo userDetail = userDetailService.getUserRankDetailPageInfoByUserId(userId);
            Date date = new Date();
            if (StringUtils.isNotBlank(userDetail.getLastPointTime())) {
                date = TimeUtils.parseTime(userDetail.getLastPointTime());
                userDetail.setLastPointTime(TimeUtils.formateDate(date));
            }
            data.put("userDetail", userDetail);
            return ApiResponse.successMessage(data);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return ApiResponse.failMessage("failed");

    }

}
