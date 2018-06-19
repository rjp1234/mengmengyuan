/**    
 * 文件名：TeacherDao.java    
 *    
 * 版本信息：    
 * 日期：2018年6月19日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.base.teacher.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 项目名称：mengmengyuan 类名称：TeacherDao 类描述： 创建人：Administrator 创建时间：2018年6月19日
 * 下午3:21:45 修改人：Administrator 修改时间：2018年6月19日 下午3:21:45 修改备注：
 * 
 * @version
 * 
 */
@Mapper
public interface TeacherDao {
    public String getNameById(String id);

}
