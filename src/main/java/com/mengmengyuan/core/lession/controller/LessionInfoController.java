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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mengmengyuan.core.base.ApiResponse;
import com.mengmengyuan.core.base.BaseController;
import com.mengmengyuan.core.lession.entity.LessionInfo;
import com.mengmengyuan.core.lession.entity.LessionPageInfo;
import com.mengmengyuan.core.lession.service.LessionInfoService;
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
    private LessionInfoService lessionInfoService;

    @Autowired
    UserInfoService userService;

    @Autowired
    StudioInfoService studioService;

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
        try {
            classId = userService.getById(userId).getClassId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        List<LessionPageInfo> pageList = new ArrayList<LessionPageInfo>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lessionList", pageList);
        // 用户未和班级绑定，获取不到课文列表，返回空列表
        if (StringUtils.isBlank(classId)) {
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
            lessionPage.setState(studioService.countStudio(userId, lession.getId(), null));// 获取当前用户对该课文的完成状态
            lessionPage.setCreateTime(lession.getCreateTime());
            pageList.add(lessionPage);
        }

        return ApiResponse.successMessage(data);

    }

}
