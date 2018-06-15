/**    
 * 文件名：LessionClassBindInfo.java    
 *    
 * 版本信息：    
 * 日期：2018年6月15日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.lession.entity;

import com.mengmengyuan.core.base.BaseEntity;

/**
 * 
 * 项目名称：mengmengyuan 类名称：LessionClassBindInfo 类描述： 创建人：Administrator
 * 创建时间：2018年6月15日 下午1:51:55 修改人：Administrator 修改时间：2018年6月15日 下午1:51:55 修改备注：
 * 
 * @version
 * 
 */
public class LessionClassBindInfo extends BaseEntity {
    private String lessionId;

    private String classId;

    public String getLessionId() {
        return lessionId;
    }

    public void setLessionId(String lessionId) {
        this.lessionId = lessionId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

}
