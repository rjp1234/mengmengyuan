/**    
 * 文件名：StudioInfoService.java    
 *    
 * 版本信息：    
 * 日期：2018年6月14日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.studio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mengmengyuan.core.base.BaseService;
import com.mengmengyuan.core.studio.dao.StudioInfoDao;
import com.mengmengyuan.core.studio.entity.StudioInfo;
import com.mengmengyuan.core.studio.entity.StudioPointRecordInfo;

/**
 * 
 * 项目名称：mengmengyuan 类名称：StudioInfoService 类描述： 创建人：Administrator
 * 创建时间：2018年6月14日 下午2:44:26 修改人：Administrator 修改时间：2018年6月14日 下午2:44:26 修改备注：
 * 
 * @version
 * 
 */
@Service
public class StudioInfoService extends BaseService {
    @Autowired
    StudioInfoDao studioInfoDao;

    /**
     * 
     * getSumPointByUserId(根据用户的id获取用户获得的评分总分)
     * 
     * 
     */
    public int getSumPointByUserId(String userId) {
        StudioInfo studio = new StudioInfo();
        studio.setUserId(userId);
        return studioInfoDao.countSumPointByUserId(studio);
    }

    /**
     * countPointTime(计算被打分次数) 通过计算pointer为null的记录个数
     * 
     */
    public int countPointTime(String userId) {
        StudioInfo studio = new StudioInfo();
        studio.setUserId(userId);
        return studioInfoDao.countPointTime(studio);
    }

    /**
     * 
     * countStudio(根据不同条件计算对应的录音数目)
     * 
     * @param lessionId
     *            为空时，则不添加该搜索条件
     * @param type为空时，则不添加该搜索条件
     * @param userId
     *            为空时，则不添加该搜索条件
     */
    public int countStudio(String userId, String lessionId, String type) {
        StudioInfo studio = new StudioInfo();
        studio.setUserId(userId);
        studio.setType(type);
        studio.setLessionId(lessionId);
        return studioInfoDao.countStudio(studio);
    }

    /**
     * 
     * insert(新增一条录音)
     * 
     * 
     */
    public int insert(StudioInfo studio) {
        return studioInfoDao.insert(studio);
    }

    /**
     * 
     * countComplete(这里用一句话描述这个方法的作用)
     * 
     */
    public int countComplete(String lessionId) {
        // TODO Auto-generated method stub
        StudioInfo studio = new StudioInfo();
        studio.setLessionId(lessionId);
        return studioInfoDao.countComplete(studio);
    }

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
    public List<StudioPointRecordInfo> getUserStudioPointRecordList(String userId, int size, String time) {
        StudioPointRecordInfo studioRecord = new StudioPointRecordInfo();
        studioRecord.setUserId(userId);
        studioRecord.setSize(size);
        studioRecord.setCreateTime(time);
        return studioInfoDao.getUserStudioPointRecordList(studioRecord);
    }

    /**
     * 
     * getByUserIdAndLessionId(这里用一句话描述这个方法的作用)
     * 
     * 
     */
    public StudioInfo getByUserIdAndLessionId(String userId, String lessionId) {
        StudioInfo studio = new StudioInfo();
        studio.setUserId(userId);
        studio.setLessionId(lessionId);

        return studioInfoDao.getByUserIdAndLessionId(studio);
    }

}
