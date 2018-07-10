/**    
 * 文件名：StudioInfo.java    
 *    
 * 版本信息：    
 * 日期：2018年6月14日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.studio.entity;

import com.mengmengyuan.core.base.BaseEntity;

/**
 * 
 * 项目名称：mengmengyuan 类名称：StudioInfo 类描述： 创建人：Administrator 创建时间：2018年6月14日
 * 下午2:29:25 修改人：Administrator 修改时间：2018年6月14日 下午2:29:25 修改备注：
 * 
 * @version 学生录音数据库传输类
 */
public class StudioInfo extends BaseEntity {

    private String url;// 录音路径

    private String userId;// 上传用户id

    private String lessionId;// 录音对应的课文id

    private int point;// 打分

    private String pointer;// 打分人

    private String pointTime;// 打分时间

    private String type;// 类型：0、朗读。1、背诵

    private int ranking;// 名次

    public static final String TYPE_READ = "0";// 朗读

    public static final String TYPE_RECITE = "1";// 背诵

    /**
     * ranking
     * 
     * @return the ranking
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public int getRanking() {
        return ranking;
    }

    /**
     * @param ranking
     *            the ranking to set
     */
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLessionId() {
        return lessionId;
    }

    public void setLessionId(String lessionId) {
        this.lessionId = lessionId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getPointer() {
        return pointer;
    }

    public void setPointer(String pointer) {
        this.pointer = pointer;
    }

    public String getPointTime() {
        return pointTime;
    }

    public void setPointTime(String pointTime) {
        this.pointTime = pointTime;
    }

}
