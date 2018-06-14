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

import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mengmengyuan.core.user.service.UserInfoService;

/**
 * 
 * 项目名称：mengmengyuan 类名称：AdminSecurityConfig 类描述： 创建人：Administrator
 * 创建时间：2018年6月14日 下午10:35:21 修改人：Administrator 修改时间：2018年6月14日 下午10:35:21 修改备注：
 * 
 * @version
 * 
 */
@Configuration
public class AdminSecurityConfig implements WebMvcConfigurer {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    LoginConfig loginConfig;

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
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/findByAdminAndPassword");
        // 拦截所有路径
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws IOException {
            String userId = request.getParameter("userId");
            String accToken = request.getParameter("accToken");
            try {
                return userInfoService.checkAccToken(userId, accToken);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return false;
            }
        }
    }

}