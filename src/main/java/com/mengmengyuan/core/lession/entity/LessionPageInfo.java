/**    
 * 文件名：LessionPageInfo.java    
 *    
 * 版本信息：    
 * 日期：2018年6月14日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.lession.entity;

import com.mengmengyuan.core.base.BaseEntity;

/**
 * 
 * 项目名称：mengmengyuan 类名称：LessionPageInfo 类描述： 创建人：Administrator 创建时间：2018年6月14日
 * 下午5:19:20 修改人：Administrator 修改时间：2018年6月14日 下午5:19:20 修改备注：
 * 
 * @version 用于返回首页面的课程列表
 */
public class LessionPageInfo extends BaseEntity {

    private String name;// 课文名称

    private String image;// 课文封面

    private String issueTime;// 下发时间

    private int unit;// 所属单元

    private int readNum;// 总朗读人数

    private int reciteNum;// 总背诵人数

    private boolean reciteState;// 背诵状态

    private boolean readState;// 朗读状态

    private int completeNum;// 完成人数

    private String userId;// 用户id

    public static final int STATE_COMPLETE = 1;

    public static final int STATE_UNFINISHED = 0;

    /**
     * userId
     * 
     * @return the userId
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * unit
     * 
     * @return the unit
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public int getUnit() {
        return unit;
    }

    /**
     * @param unit
     *            the unit to set
     */
    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(int completeNum) {
        this.completeNum = completeNum;
    }

    public boolean isReciteState() {
        return reciteState;
    }

    public void setReciteState(boolean reciteState) {
        this.reciteState = reciteState;
    }

    public boolean isReadState() {
        return readState;
    }

    public void setReadState(boolean readState) {
        this.readState = readState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public int getReciteNum() {
        return reciteNum;
    }

    public void setReciteNum(int reciteNum) {
        this.reciteNum = reciteNum;
    }

}
