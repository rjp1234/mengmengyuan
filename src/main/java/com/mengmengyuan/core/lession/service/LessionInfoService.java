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
import com.mengmengyuan.core.lession.entity.LessionDetailPageInfo;
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

    /**
     * 
     * getById(这里用一句话描述这个方法的作用)
     * 
     * 
     */
    public LessionInfo getById(String lessionId) {
        LessionInfo lession = new LessionInfo();
        lession.setId(lessionId);
        return lessionDao.get(lession);
    }

    /**
     * 
     * countLession(这里用一句话描述这个方法的作用)
     * 
     */
    public int countLession(String classId) {
        LessionInfo lession = new LessionInfo();
        lession.setClassId(classId);
        return lessionDao.countLession(lession);
    }

    /**
     * 
     * getLessionDetailPageInfoByLessionIdAndUserId(这里用一句话描述这个方法的作用)
     * 
     * TODO(这里描述这个方法适用条件 – 可选)
     * 
     * TODO(这里描述这个方法的执行流程 – 可选)
     * 
     * TODO(这里描述这个方法的使用方法 – 可选)
     * 
     * TODO(这里描述这个方法的注意事项 – 可选)
     * 
     * @param name
     * 
     * @param @return
     *            设定文件
     * 
     * @return String DOM对象
     * 
     * @Exception 异常对象
     * 
     * @since CodingExample Ver(编码范例查看) 1.1
     * 
     */
    public LessionDetailPageInfo getLessionDetailPageInfoByLessionIdAndUserId(String lessionId, String userId) {
        LessionDetailPageInfo detail = new LessionDetailPageInfo();
        detail.setId(lessionId);
        detail.setUserId(userId);
        return lessionDao.getLessionDetailPageInfoByLessionIdAndUserId(detail);
    }

}
