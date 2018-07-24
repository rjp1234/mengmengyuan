/**    
 * 文件名：LessionClassBindDao.java    
 *    
 * 版本信息：    
 * 日期：2018年6月15日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.lession.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mengmengyuan.core.base.BaseDao;
import com.mengmengyuan.core.lession.entity.LessionClassBindInfo;
import com.mengmengyuan.core.lession.entity.LessionPageInfo;

/**
 * 
 * 项目名称：mengmengyuan 类名称：LessionClassBindDao 类描述： 创建人：Administrator
 * 创建时间：2018年6月15日 下午1:52:29 修改人：Administrator 修改时间：2018年6月15日 下午1:52:29 修改备注：
 * 
 * @version
 * 
 */
@Mapper
public interface LessionClassBindDao extends BaseDao<LessionClassBindInfo> {

    /**
     * 
     * getMaxIssueClass(获取课文下发最多的那个班级的classId)
     * 
     * 
     */
    String getMaxIssueClass();

    /**
     * 
     * countTouristLession(计算游客当前浏览的班级的课文总数)
     * 
     */
    int countTouristLession(LessionPageInfo lession);

    /**
     * 
     * countLession(这里用一句话描述这个方法的作用)
     * 
     */
    int countLession(LessionPageInfo lession);

}
