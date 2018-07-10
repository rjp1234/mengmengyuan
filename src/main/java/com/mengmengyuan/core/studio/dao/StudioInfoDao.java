/**    
 * 文件名：StudioDao.java    
 *    
 * 版本信息：    
 * 日期：2018年6月14日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.studio.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mengmengyuan.core.base.BaseDao;
import com.mengmengyuan.core.studio.entity.StudioInfo;
import com.mengmengyuan.core.studio.entity.StudioPointRecordInfo;

/**
 * 
 * 项目名称：mengmengyuan 类名称：StudioDao 类描述： 创建人：Administrator 创建时间：2018年6月14日
 * 下午2:52:48 修改人：Administrator 修改时间：2018年6月14日 下午2:52:48 修改备注：
 * 
 * @version
 * 
 */
@Mapper
public interface StudioInfoDao extends BaseDao<StudioInfo> {

    /**
     * 
     * countSumPointByUserId(根据用户的id获取用户获得的评分总分)
     */
    int countSumPointByUserId(StudioInfo studio);

    /**
     * 
     * countPointTime(计算某用户打分次数)
     * 
     */
    int countPointTime(StudioInfo studio);

    /**
     * 
     * countStudio(计算朗读或背诵类型的录音完成数)
     * 
     */
    int countStudio(StudioInfo studio);

    /**
     * 
     * countComplete(计算完成该课文朗读背诵的学生总数)
     * 
     */
    int countComplete(StudioInfo studio);

    /**
     * 
     * getUserStudioPointRecordList(这里用一句话描述这个方法的作用) 按照createTime排序
     * 
     * @param time
     *            分割时间，返回createTime晚于该时间节点的信息
     * @param size
     *            分割量，返回不超过这个数量的信息
     * 
     * 
     * 
     */
    List<StudioPointRecordInfo> getUserStudioPointRecordList(StudioPointRecordInfo studioRecord);

    /**
     * 
     * getByUserIdAndLessionId(这里用一句话描述这个方法的作用)
     */
    StudioInfo getByUserIdAndLessionId(StudioInfo studio);

}
