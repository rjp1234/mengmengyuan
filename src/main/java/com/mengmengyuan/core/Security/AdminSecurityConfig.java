/**    
 * 文件名：AdminSecurityConfig.java    
 *    
 * 版本信息：    
 * 日期：2018年6月14日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.Security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mengmengyuan.common.ReturnConstants;
import com.mengmengyuan.core.base.ApiResponse;
import com.mengmengyuan.core.user.service.UserInfoService;

import net.sf.json.JSONObject;

/**
 * 
 * 项目名称：mengmengyuan 类名称：AdminSecurityConfig 类描述： 创建人：Administrator
 * 创建时间：2018年6月14日 下午10:35:21 修改人：Administrator 修改时间：2018年6月14日 下午10:35:21 修改备注：
 * 
 * @version 用户登录拦截器
 * 
 */
@Configuration
public class AdminSecurityConfig implements WebMvcConfigurer {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserInfoService userInfoService;

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        // 排除的路径
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/user/login");
        addInterceptor.excludePathPatterns("/studio/uploadStudio");
        // 拦截所有路径
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws IOException {
            String userId = request.getParameter("userId");
            String accToken = request.getParameter("accToken");
            ApiResponse apiResponse = null;
            // 用户id校验为空
            if (StringUtils.isBlank(userId) || userId.length() != 32) {
                apiResponse = ApiResponse.failMessage(ReturnConstants.ERROR_USERID_INVALID,
                        "user id is empty or invalid");
                response.getWriter().append(JSONObject.fromObject(apiResponse).toString());
                return false;
            }
            // 用户accToken校验为空
            if (StringUtils.isBlank(accToken)) {
                apiResponse = ApiResponse.failMessage(ReturnConstants.ERROR_ACCTOKEN_INVALID, "accToken is empty");
                response.getWriter().append(JSONObject.fromObject(apiResponse).toString());
                return false;
            }
            // 联合数据库同步校验
            boolean flag = false;
            try {
                flag = userInfoService.checkAccToken(userId, accToken);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            if (flag) {
                // 校验通过
                return true;
            }

            // accToken失效
            apiResponse = ApiResponse.failMessage(ReturnConstants.ERROR_ACCTOKEN_INVALID, "accToken is lose efficacy");
            response.getWriter().append(JSONObject.fromObject(apiResponse).toString());
            return false;
        }
    }

}