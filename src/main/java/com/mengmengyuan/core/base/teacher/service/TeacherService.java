/**    
 * 文件名：TeacherService.java    
 *    
 * 版本信息：    
 * 日期：2018年6月19日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.base.teacher.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mengmengyuan.common.ThreadPool;
import com.mengmengyuan.core.base.BaseService;
import com.mengmengyuan.core.base.teacher.dao.TeacherDao;

/**
 * 
 * 项目名称：mengmengyuan 类名称：TeacherService 类描述： 创建人：Administrator 创建时间：2018年6月19日
 * 下午3:20:47 修改人：Administrator 修改时间：2018年6月19日 下午3:20:47 修改备注：
 * 
 * @version
 * 
 */
@Service
public class TeacherService extends BaseService {
    @Autowired
    TeacherDao dao;

    public final static Map<String, String> bufferMap = new ConcurrentHashMap<String, String>();

    /**
     * 
     * getTNameById(根据id获取教师名称)
     * 
     */
    public String getTNameById(String id) {
        String name = bufferMap.get(id);
        if (StringUtils.isNotBlank(name)) {
            ThreadPool.getInstance().execute(() -> {
                String nameSearch = dao.getNameById(id);
                if (nameSearch != null) {
                    bufferMap.put(id, nameSearch);
                }
            });
            return name;
        }
        name = dao.getNameById(id);
        if (name != null) {
            bufferMap.put(id, name);
        }
        return name;
    }

}
