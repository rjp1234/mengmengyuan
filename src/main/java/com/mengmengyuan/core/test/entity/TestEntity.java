/**    
 * 文件名：TestEntity.java    
 *    
 * 版本信息：    
 * 日期：2018年5月31日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.test.entity;

/**
 * 
 * 项目名称：mengmengyuan 类名称：TestEntity 类描述： 创建人：Administrator 创建时间：2018年5月31日
 * 下午11:11:31 修改人：Administrator 修改时间：2018年5月31日 下午11:11:31 修改备注：
 * 
 * @version
 * 
 */
public class TestEntity {
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestEntity [id=" + id + ", name=" + name + "]";
    }

}
