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
import com.mengmengyuan.common.util.TimeUtils;
import com.mengmengyuan.core.base.ApiResponse;
import com.mengmengyuan.core.base.BaseController;
import com.mengmengyuan.core.base.teacher.service.TeacherService;
import com.mengmengyuan.core.lession.entity.LessionDetailPageInfo;
import com.mengmengyuan.core.lession.entity.LessionPageInfo;
import com.mengmengyuan.core.lession.service.LessionClassBindService;
import com.mengmengyuan.core.lession.service.LessionInfoService;
import com.mengmengyuan.core.studio.entity.LessionRankingPageInfo;
import com.mengmengyuan.core.studio.entity.StudioInfo;
import com.mengmengyuan.core.studio.service.StudioInfoService;
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
        List<LessionPageInfo> lessionList = new ArrayList<LessionPageInfo>();
        int pageNo = PAGE_NO;
        int pageSize = PAGE_SIZE;
        Map<String, Object> data = new HashMap<String, Object>();
        // 用户未和班级绑定，获取不到课文列表，返回空列表
        if (StringUtils.isBlank(userId)) {
            return ApiResponse.successMessage(data);
        }
        // 对分页条件进行处理
        if (StringUtils.isNumeric(pageNoStr) && StringUtils.isNumeric(pageSizeStr)) {
            pageNo = Integer.parseInt(pageNoStr);
            pageSize = Integer.parseInt(pageSizeStr);
        }
        // 获取课程列表
        try {
            lessionList = lessionInfoService.getPage(userId, pageNo, pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        // 组装返回页面的对象
        for (LessionPageInfo lession : lessionList) {
            lession.setName(StringEscapeUtils.unescapeHtml(lession.getName()));
            lession.setIssueTime(TimeUtils.turnFormat1(lession.getIssueTime()));
        }
        data.put("lessionList", lessionList);
        // 获取课文总数
        int total = lessionInfoService.countLession(userId);
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
        if (StringUtils.isBlank(lessionId)) {
            return ApiResponse.failMessage(ReturnConstants.ERROR_LESSION_ID_INVALID, "lession id is empty");
        }
        LessionDetailPageInfo detail = new LessionDetailPageInfo();
        detail.setContent("页面加载出错，请联系管理员解决");
        try {

            detail = lessionInfoService.getLessionDetailPageInfoByLessionIdAndUserId(lessionId, userId);
            detail.setName(StringEscapeUtils.unescapeHtml(detail.getName()));
            detail.setContent(StringEscapeUtils.unescapeHtml(detail.getContent()));
            detail.settContent(StringEscapeUtils.unescapeHtml(detail.gettContent()));
            detail.setCreater(teacherService.getTNameById(detail.getCreater()));
            // detail.setCreateTime(TimeUtils.turnFormat1(detail.getCreateTime()));
            detail.setIssueTime(TimeUtils.turnFormat1(detail.getIssueTime()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("detail", detail);
        return ApiResponse.successMessage(data);
    }

    /**
     * 
     * lessionRankingDetail(获取某课文下用户排行榜信息)
     * 
     * 
     */
    @RequestMapping("lessionRankingDetail")
    public ApiResponse lessionRankingDetail(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String lessionId = request.getParameter("lessionId");
        List<LessionRankingPageInfo> rankList = new ArrayList<>();
        try {

            rankList = studioService.getLessionRankingPageList(lessionId, userId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        StudioInfo userStudio = null;
        try {

            userStudio = studioService.getByUserIdAndLessionId(userId, lessionId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        LessionDetailPageInfo lession = new LessionDetailPageInfo();
        try {
            LessionDetailPageInfo l = lessionInfoService.getLessionDetailPageInfoByLessionIdAndUserId(lessionId,
                    userId);
            // 减轻数据传送压力
            lession.setName(StringEscapeUtils.unescapeHtml(l.getName()));
            lession.setIssueTime(TimeUtils.turnFormat1(l.getIssueTime()));
            lession.setImage(l.getImage());

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("rankList", rankList);
        data.put("studio", userStudio);
        data.put("lession", lession);
        return ApiResponse.successMessage(data);

    }

}
