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

import java.text.ParseException;
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

    static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM");

    /**
     * 
     * formatNowDay(yyyy-MM 返回当前日期)
     * 
     */
    public static String formatNowDay() {
        return sdf3.format(new Date());
    }

    /**
     * 
     * formateDate(将制定日期转换成 yyyy-MM-dd HH:mm:ss 的形式)
     * 
     */
    public static String formateDate(Date date) {

        return sdf2.format(date);

    }

    /**
     * turnFormat1
     * 
     * yyyy-MM-dd HH:mm:ss.SSS ->yyyy-MM-dd HH:mm:ss
     */
    public static String turnFormat1(String time) {

        return time.substring(0, time.indexOf("."));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(turnFormat1("2018-12-11 12:22:11.111"));
    }

    /**
     * 
     * formateNowDay2yyyy-MM-dd HH:mm:ss.SSS 返回当前日期
     * 
     * 
     */
    public static String formateNowDay2() {
        return sdf1.format(new Date());
    }

    public synchronized static Date parseTime(String time) {
        try {
            return sdf1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

}
