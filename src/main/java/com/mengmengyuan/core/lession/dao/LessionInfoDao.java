/**    
 * 文件名：LessionInfoDao.java    
 *    
 * 版本信息：    
 * 日期：2018年6月14日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.lession.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mengmengyuan.core.base.BaseDao;
import com.mengmengyuan.core.lession.entity.LessionDetailPageInfo;
import com.mengmengyuan.core.lession.entity.LessionInfo;
import com.mengmengyuan.core.lession.entity.LessionPageInfo;

/**
 * 
 * 项目名称：mengmengyuan 类名称：LessionInfoDao 类描述： 创建人：Administrator 创建时间：2018年6月14日
 * 下午4:16:21 修改人：Administrator 修改时间：2018年6月14日 下午4:16:21 修改备注：
 * 
 * @version
 * 
 */
@Mapper
public interface LessionInfoDao extends BaseDao<LessionInfo> {

    /**
     * 
     * getList(这里用一句话描述这个方法的作用)
     * 
     */
    List<LessionPageInfo> getList(LessionPageInfo lession);

    /**
     * 
     * countLession(这里用一句话描述这个方法的作用)
     * 
     */
    int countLession(LessionPageInfo lession);

    /**
     * 
     * getLessionDetailPageInfoByLessionIdAndUserId(这里用一句话描述这个方法的作用)
     * 
     */
    LessionDetailPageInfo getLessionDetailPageInfoByLessionIdAndUserId(LessionDetailPageInfo detail);

}
