/**    
 * 文件名：BaseDao.java    
 *    
 * 版本信息：    
 * 日期：2018年6月1日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.base;

import java.util.List;

/**
 * 
 * 项目名称：mengmengyuan 类名称：BaseDao 类描述： 创建人：Administrator 创建时间：2018年6月1日 下午5:39:48
 * 修改人：Administrator 修改时间：2018年6月1日 下午5:39:48 修改备注：
 * 
 * @version
 * 
 */
public interface BaseDao<T extends BaseEntity> {
    public int insert(T t);

    public int delete(T t);

    public int update(T t);

    public List<T> getAll(T t);

    public T get(T t);

}
