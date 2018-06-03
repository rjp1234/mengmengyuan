/**    
 * 文件名：TestDao.java    
 *    
 * 版本信息：    
 * 日期：2018年5月31日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.test.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mengmengyuan.core.test.entity.TestEntity;

/**
 * 
 * 项目名称：mengmengyuan 类名称：TestDao 类描述： 创建人：Administrator 创建时间：2018年5月31日
 * 下午10:33:39 修改人：Administrator 修改时间：2018年5月31日 下午10:33:39 修改备注：
 * 
 * @version
 * 
 */
@Mapper
public interface TestDao {
    public int insert(TestEntity test);

    public TestEntity select(TestEntity test);

    public int deleteAll();

}
