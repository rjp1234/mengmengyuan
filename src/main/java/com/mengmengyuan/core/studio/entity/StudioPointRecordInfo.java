/**    
 * 文件名：StudioPointRecordInfo.java    
 *    
 * 版本信息：    
 * 日期：2018年7月9日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.studio.entity;

import com.mengmengyuan.core.base.BaseEntity;

/**
 * 
 * 项目名称：mengmengyuan 类名称：StudioPointRecordInfo 类描述： 创建人：Administrator
 * 创建时间：2018年7月9日 下午1:16:44 修改人：Administrator 修改时间：2018年7月9日 下午1:16:44 修改备注：
 * 
 * @version
 * 
 */
public class StudioPointRecordInfo extends BaseEntity {
    private String userId;

    private String lessionId;

    private String createTime;

    private String pointTime;

    private String comment;

    private int point;

    private int size;// 分页参数

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLessionId() {
        return lessionId;
    }

    public void setLessionId(String lessionId) {
        this.lessionId = lessionId;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPointTime() {
        return pointTime;
    }

    public void setPointTime(String pointTime) {
        this.pointTime = pointTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

}
