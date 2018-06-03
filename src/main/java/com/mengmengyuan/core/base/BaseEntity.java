/**    
 * 文件名：BaseEntity.java    
 *    
 * 版本信息：    
 * 日期：2018年6月1日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.base;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 
 * 项目名称：mengmengyuan 类名称：BaseEntity 类描述： 创建人：Administrator 创建时间：2018年6月1日
 * 下午5:13:14 修改人：Administrator 修改时间：2018年6月1日 下午5:13:14 修改备注：
 * 
 * @version
 * 
 */
public class BaseEntity {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
