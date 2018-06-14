/**    
 * 文件名：LessionInfoService.java    
 *    
 * 版本信息：    
 * 日期：2018年6月14日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.lession.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mengmengyuan.core.base.BaseService;
import com.mengmengyuan.core.lession.dao.LessionInfoDao;
import com.mengmengyuan.core.lession.entity.LessionInfo;

/**
 * 
 * 项目名称：mengmengyuan 类名称：LessionInfoService 类描述： 创建人：Administrator
 * 创建时间：2018年6月14日 下午4:17:53 修改人：Administrator 修改时间：2018年6月14日 下午4:17:53 修改备注：
 * 
 * @version
 * 
 */

@Service
public class LessionInfoService extends BaseService {
    @Autowired
    LessionInfoDao lessionDao;

    /**
     * 
     * getPage(根据班级id获取下发的课程列表，并进行分页后)
     * 
     * 
     */
    public List<LessionInfo> getPage(String classId, int pageNo, int pageSize) {
        int limit = pageSize;
        int skip = (pageNo - 1) * pageSize;
        LessionInfo lession = new LessionInfo();
        lession.setLimit(limit);
        lession.setSkip(skip);
        lession.setClassId(classId);// 设置课文被下发的班级
        return lessionDao.getList(lession);
    }

}
