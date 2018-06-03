/**    
 * 文件名：TimeUtils.java    
 *    
 * 版本信息：    
 * 日期：2018年6月2日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 项目名称：mengmengyuan 类名称：TimeUtils 类描述： 创建人：Administrator 创建时间：2018年6月2日
 * 下午4:34:50 修改人：Administrator 修改时间：2018年6月2日 下午4:34:50 修改备注：
 * 
 * @version
 * 
 */
public class TimeUtils {
    /**
     * sdf1:TODO（yyyy-MM-dd HH:mm:ss.SSS）
     * 
     * @since Ver 1.1
     */
    static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 
     * formatNowDay(yyyy-MM-dd 返回当前日期)
     * 
     */
    public static String formatNowDay() {
        return sdf2.format(new Date());
    }

}
