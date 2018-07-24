/**    
 * 文件名：LessionClassInfoBindService.java    
 *    
 * 版本信息：    
 * 日期：2018年6月15日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.lession.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mengmengyuan.core.base.BaseService;
import com.mengmengyuan.core.lession.dao.LessionClassBindDao;
import com.mengmengyuan.core.lession.entity.LessionClassBindInfo;
import com.mengmengyuan.core.lession.entity.LessionPageInfo;

/**
 * 
 * 项目名称：mengmengyuan 类名称：LessionClassInfoBindService 类描述： 创建人：Administrator
 * 创建时间：2018年6月15日 下午1:51:14 修改人：Administrator 修改时间：2018年6月15日 下午1:51:14 修改备注：
 * 
 * @version
 * 
 */
@Service
public class LessionClassBindService extends BaseService {
    @Autowired
    LessionClassBindDao dao;

    public LessionClassBindInfo get(String lessionId, String classId) {
        LessionClassBindInfo bind = new LessionClassBindInfo();
        bind.setClassId(classId);
        bind.setLessionId(lessionId);
        return dao.get(bind);
    }

    /**
     * 
     * getMaxIssueClass(获取当前下发课文最多的那个班级)
     * 
     * 
     */
    public String getMaxIssueClass() {
        return dao.getMaxIssueClass();
    }

    /**
     * 
     * countTouristLession(游客模式使用，查看游客当前浏览的班级全部下发课文的总数)
     * 
     * 
     */
    public int countTouristLession(String classId) {
        LessionPageInfo lession = new LessionPageInfo();
        lession.setClassId(classId);
        return dao.countTouristLession(lession);
    }

    /**
     * 
     * countLession(根据用户id去查该用户所在班级下发的课文总数)
     * 
     */
    public int countLession(String userId) {
        LessionPageInfo lession = new LessionPageInfo();
        lession.setUserId(userId);

        return dao.countLession(lession);
    }

}
