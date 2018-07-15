/**    
 * 文件名：StudioController.java    
 *    
 * 版本信息：    
 * 日期：2018年6月20日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.studio.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.mengmengyuan.common.ReturnConstants;
import com.mengmengyuan.common.util.FileLoadUtils;
import com.mengmengyuan.common.util.IdGen;
import com.mengmengyuan.common.util.TimeUtils;
import com.mengmengyuan.core.base.ApiResponse;
import com.mengmengyuan.core.base.BaseController;
import com.mengmengyuan.core.studio.entity.StudioInfo;
import com.mengmengyuan.core.studio.entity.StudioPointRecordInfo;
import com.mengmengyuan.core.studio.service.StudioInfoService;

/**
 * 
 * 项目名称：mengmengyuan 类名称：StudioController 类描述： 创建人：Administrator 创建时间：2018年6月20日
 * 下午12:53:27 修改人：Administrator 修改时间：2018年6月20日 下午12:53:27 修改备注：
 * 
 * @version
 * 
 */
@RequestMapping(path = "studio", method = { RequestMethod.POST })
@RestController
public class StudioController extends BaseController {
    @Autowired
    private StudioInfoService studioService;

    @RequestMapping("uploadStudio")
    public ApiResponse uploadStudio(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String lessionId = request.getParameter("lessionId");
        String type = request.getParameter("type");
        InputStream input = null;
        try {
            input = getFileFromRequest(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (input == null) {
            logger.error("input  is null ------userId=" + userId + "&lessionId=" + lessionId + "&type=" + type);
            return ApiResponse.failMessage(ReturnConstants.ERROR_STUDIO_UPLOAD_FAILED, "studio upload faild state 1");

        }
        String studioId = IdGen.uuid();
        String studioUrl = null;
        try {
            studioUrl = FileLoadUtils.QIniuupload(FileLoadUtils.SOURCE_TYPE_STUDIO_STU, input, studioId + ".mp3");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        // 校验上传过程中没有报错
        if (StringUtils.isBlank(studioUrl)) {
            logger.error("studioUrl is empty---userId=" + userId + "&lessionId=" + lessionId + "&type=" + type);
            return ApiResponse.failMessage(ReturnConstants.ERROR_STUDIO_UPLOAD_FAILED, "studio upload faild state 2");
        }

        // 类型判断
        if (StringUtils.isBlank(type) || (!type.equals(StudioInfo.TYPE_READ) && !type.equals(StudioInfo.TYPE_RECITE))) {
            logger.error("type is invalid---userId=" + userId + "&lessionId=" + lessionId + "&type=" + type);
            return ApiResponse.failMessage(ReturnConstants.ERROR_STUDIO_TYPE_INVALID, "type is invalid");

        }
        // 判断该学生是否已经上传对应类型的studio
        int count = studioService.countStudio(userId, lessionId, type);
        if (count > 0) {
            logger.error("same type studio had already upload--userId=" + userId + "&lessionId=" + lessionId + "&type="
                    + type);
            return ApiResponse.failMessage(ReturnConstants.ERROR_SAME_TYPE_STUDIO_ALREADY_UPLOAD,
                    "same type studio had already upload");
        }

        if (StringUtils.isBlank(lessionId)) {
            logger.error("lession id is empty---userId=" + userId + "&lessionId=" + lessionId + "&type=" + type);
            return ApiResponse.failMessage(ReturnConstants.ERROR_LESSION_ID_INVALID, "lession id is empty");
        }

        StudioInfo studio = new StudioInfo();
        studio.setId(IdGen.uuid());
        studio.setLessionId(lessionId);
        studio.setCreateTime(TimeUtils.formateNowDay2());
        studio.setUrl(studioUrl);
        studio.setType(type);
        studio.setUserId(userId);
        studioService.insert(studio);
        return ApiResponse.successMessage(null);

    }

    /**
     * 
     * userStudioPointRecordList(1、 个人评分记录列表（依据课程名称分行） 列表包含内容： 课程名称、完成时间、评分、评语 )
     * 
     */
    @RequestMapping("userStudioPointRecordList")
    public ApiResponse userStudioPointRecordList(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String sizeStr = request.getParameter("size");
        String time = request.getParameter("time");
        int size = PAGE_SIZE;
        if (StringUtils.isNumeric(sizeStr)) {
            size = Integer.parseInt(sizeStr);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        try {

            List<StudioPointRecordInfo> recordList = studioService.getUserStudioPointRecordList(userId, size, time);
            for (StudioPointRecordInfo studioPointRecordInfo : recordList) {
                try {
                    studioPointRecordInfo
                            .setComment(StringEscapeUtils.unescapeHtml(studioPointRecordInfo.getComment()));
                    studioPointRecordInfo.setName(StringEscapeUtils.unescapeHtml(studioPointRecordInfo.getName()));
                    studioPointRecordInfo.setCreateTime(TimeUtils.turnFormat1(studioPointRecordInfo.getCreateTime()));
                    if (StringUtils.isNotBlank(studioPointRecordInfo.getPointTime())) {
                        studioPointRecordInfo.setPointTime(TimeUtils.turnFormat1(studioPointRecordInfo.getPointTime()));
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }

            data.put("recordList", recordList);
            data.put("time", recordList.get(recordList.size() - 1).getCreateTime());

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return ApiResponse.successMessage(data);

    }

    /**
     * 
     * getFileFromRequest(从微信请求中获取输入流）
     * 
     * 
     */
    public InputStream getFileFromRequest(HttpServletRequest request) {
        try {
            StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
            Iterator<String> iterator = req.getFileNames();
            while (iterator.hasNext()) {
                MultipartFile files = req.getFile(iterator.next());
                return files.getInputStream();
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

}
