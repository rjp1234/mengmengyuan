/**    
 * 文件名：TestService.java    
 *    
 * 版本信息：    
 * 日期：2018年5月31日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mengmengyuan.core.test.dao.TestDao;
import com.mengmengyuan.core.test.entity.TestEntity;

/**
 * 
 * 项目名称：mengmengyuan 类名称：TestService 类描述： 创建人：Administrator 创建时间：2018年5月31日
 * 下午10:32:54 修改人：Administrator 修改时间：2018年5月31日 下午10:32:54 修改备注：
 * 
 * @version
 * 
 */
@Service
public class TestService {
    @Autowired
    TestDao testDao;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mengmengyuan.core.test.dao.TestDao#insert(com.mengmengyuan.core.test.
     * entity.TestEntity)
     */
    public int insert(TestEntity test) {
        // TODO Auto-generated method stub
        return testDao.insert(test);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.mengmengyuan.core.test.dao.TestDao#select(com.mengmengyuan.core.test.
     * entity.TestEntity)
     */
    public TestEntity select(TestEntity test) {
        // TODO Auto-generated method stub
        return testDao.select(test);
    }

    public int delete() {
        return testDao.deleteAll();
    }

}
