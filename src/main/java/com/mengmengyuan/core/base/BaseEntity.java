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

    private String delFlag;

    private String createTime;

    private int limit;

    private int skip;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

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

    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    public static final String DEL_FLAG_NORMAL = "0";

    public static final String DEL_FLAG_DELETE = "1";

    public static final String DEL_FLAG_AUDIT = "2";
}
