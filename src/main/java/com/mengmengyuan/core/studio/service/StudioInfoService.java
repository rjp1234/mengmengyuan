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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mengmengyuan.common.util.IdGen;
import com.mengmengyuan.common.util.TimeUtils;
import com.mengmengyuan.core.base.BaseService;
import com.mengmengyuan.core.studio.dao.StudioInfoDao;
import com.mengmengyuan.core.studio.entity.StudioInfo;

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
        studio.setId(IdGen.uuid());
        studio.setCreateTime(TimeUtils.formateNowDay2());
        return studioInfoDao.insert(studio);
    }

}
