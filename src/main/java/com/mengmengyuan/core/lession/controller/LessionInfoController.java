/**    
 * 文件名：LessionInfoController.java    
 *    
 * 版本信息：    
 * 日期：2018年6月14日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.lession.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mengmengyuan.common.ReturnConstants;
import com.mengmengyuan.core.base.ApiResponse;
import com.mengmengyuan.core.base.BaseController;
import com.mengmengyuan.core.base.teacher.service.TeacherService;
import com.mengmengyuan.core.lession.entity.LessionClassBindInfo;
import com.mengmengyuan.core.lession.entity.LessionDetailPageInfo;
import com.mengmengyuan.core.lession.entity.LessionInfo;
import com.mengmengyuan.core.lession.entity.LessionPageInfo;
import com.mengmengyuan.core.lession.service.LessionClassBindService;
import com.mengmengyuan.core.lession.service.LessionInfoService;
import com.mengmengyuan.core.studio.entity.StudioInfo;
import com.mengmengyuan.core.studio.service.StudioInfoService;
import com.mengmengyuan.core.user.entity.UserInfo;
import com.mengmengyuan.core.user.service.UserInfoService;

/**
 * 
 * 项目名称：mengmengyuan 类名称：LessionInfoController 类描述： 创建人：Administrator
 * 创建时间：2018年6月14日 下午5:25:49 修改人：Administrator 修改时间：2018年6月14日 下午5:25:49 修改备注：
 * 
 * @version
 * 
 */
@RestController
@RequestMapping(path = "lession", method = { RequestMethod.POST })
public class LessionInfoController extends BaseController {
    @Autowired
    LessionInfoService lessionInfoService;

    @Autowired
    LessionClassBindService bindService;

    @Autowired
    UserInfoService userService;

    @Autowired
    StudioInfoService studioService;

    @Autowired
    TeacherService teacherService;

    /**
     * 
     * lessionList(获取课程列表)
     */
    @RequestMapping("lessionList")
    public ApiResponse lessionList(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String pageNoStr = request.getParameter("pageNo");
        String pageSizeStr = request.getParameter("pageSize");
        List<LessionInfo> lessionList = new ArrayList<LessionInfo>();
        int pageNo = PAGE_NO;
        int pageSize = PAGE_SIZE;
        String classId = null;
        UserInfo user = new UserInfo();
        try {
            user = userService.getById(userId);
            classId = user.getClassId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        List<LessionPageInfo> pageList = new ArrayList<LessionPageInfo>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lessionList", pageList);
        // 用户未和班级绑定，获取不到课文列表，返回空列表
        if (StringUtils.isBlank(classId)) {
            logger.error("userId:" + user.getId() + "has no classId");
            return ApiResponse.successMessage(data);
        }
        // 对分页条件进行处理
        if (StringUtils.isNumeric(pageNoStr) && StringUtils.isNumeric(pageSizeStr)) {
            pageNo = Integer.parseInt(pageNoStr);
            pageSize = Integer.parseInt(pageSizeStr);
        }
        // 获取课程列表
        try {
            lessionList = lessionInfoService.getPage(classId, pageNo, pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        // 组装返回页面的对象
        for (LessionInfo lession : lessionList) {
            LessionPageInfo lessionPage = new LessionPageInfo();
            lessionPage.setId(lession.getId());
            lessionPage.setImage(lession.getImage());
            lessionPage.setName(lession.getName());
            lessionPage.setReadNum(studioService.countStudio(null, lession.getId(), StudioInfo.TYPE_READ));// 获取课文总朗读次数
            lessionPage.setReciteNum(studioService.countStudio(null, lession.getId(), StudioInfo.TYPE_RECITE));// 获取课文总背诵次数
            lessionPage.setReadState(studioService.countStudio(userId, lession.getId(), StudioInfo.TYPE_READ));// 获取当前用户对该课文的朗读完成状态
            lessionPage.setReciteState(studioService.countStudio(userId, lession.getId(), StudioInfo.TYPE_RECITE));
            String issueTime = bindService.get(lession.getId(), classId).getCreateTime();
            int start = issueTime.indexOf(".");
            lessionPage.setIssueTime(issueTime.substring(0, start));
            pageList.add(lessionPage);
        }
        // 获取课文总数
        int total = lessionInfoService.countLession(classId);
        data.put("total", total);

        return ApiResponse.successMessage(data);

    }

    /**
     * 
     * lessionForm(获取课文详情页面)
     * 
     * 
     */
    @RequestMapping("lessionForm")
    public ApiResponse lessionForm(HttpServletRequest request, HttpServletResponse response) {
        String lessionId = request.getParameter("lessionId");
        String userId = request.getParameter("userId");
        UserInfo user = null;
        String classId = null;

        // 获取用户信息(班级id)
        try {
            user = userService.getById(userId);
            classId = user.getClassId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if (StringUtils.isBlank(lessionId)) {
            return ApiResponse.failMessage(ReturnConstants.ERROR_LESSION_ID_INVALID, "lession id is empty");
        }
        LessionInfo lession = null;
        try {
            lession = lessionInfoService.getById(lessionId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (lession == null) {
            return ApiResponse.failMessage(ReturnConstants.ERROR_LESSION_ID_INVALID, "该课文不存在");
        }
        LessionClassBindInfo bind = null;
        try {
            bind = bindService.get(lessionId, classId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (bind == null) {
            return ApiResponse.failMessage(ReturnConstants.ERROR_LESSION_IS_NOT_ISSUE, "lession is not issue");

        }

        LessionDetailPageInfo detail = new LessionDetailPageInfo();
        try {
            // 组装课文返回对象
            detail.setId(lession.getId());
            detail.setName(lession.getName());
            detail.setContent(StringEscapeUtils.unescapeHtml(lession.getContent()));
            detail.setImage(lession.getImage());
            detail.setCompleteNum(studioService.countComplete(lessionId));
            detail.setIssueTime(bind.getCreateTime().substring(0, bind.getCreateTime().indexOf(".")));
            detail.setExampleUrl(lession.getExampleUrl());
            detail.settContent(StringEscapeUtils.unescapeHtml(lession.gettContent()));
            detail.settStudioUrl(lession.gettStudioUrl());
            detail.setReadState(studioService.countStudio(userId, lessionId, StudioInfo.TYPE_READ));
            detail.setReciteState(studioService.countStudio(userId, lessionId, StudioInfo.TYPE_RECITE));
            detail.setCreater(teacherService.getTNameById(lession.getCreater()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("detail", detail);
        return ApiResponse.successMessage(data);

    }

}
