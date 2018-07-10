/**    
 * 文件名：LessionInfo.java    
 *    
 * 版本信息：    
 * 日期：2018年6月11日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.lession.entity;

import com.mengmengyuan.core.base.BaseEntity;

/**
 * 
 * 项目名称：mmg-manager 类名称：LessionInfo 类描述： 创建人：Administrator 创建时间：2018年6月11日
 * 上午9:55:08 修改人：Administrator 修改时间：2018年6月11日 上午9:55:08 修改备注：
 * 
 * @version lessioninfo数据库传输类
 * 
 */
public class LessionInfo extends BaseEntity {

    private String name;// 课文名

    private String content;// 课文内容

    private String image;// 课文封面

    private String tStudioUrl;// 教师的录音内容

    private String tContent;// 教师的话文本内容

    private String exampleUrl;// 示范录音

    private int unit;// 课文所在的单元

    private String textId;// 课文对应的教材id

    private String creater;// 创建人

    private String createTime;// 创建时间

    private String issueTime;

    private String classId;

    /**
     * issueTime
     * 
     * @return the issueTime
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public String getIssueTime() {
        return issueTime;
    }

    /**
     * @param issueTime
     *            the issueTime to set
     */
    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String gettStudioUrl() {
        return tStudioUrl;
    }

    public void settStudioUrl(String tStudioUrl) {
        this.tStudioUrl = tStudioUrl;
    }

    public String gettContent() {
        return tContent;
    }

    public void settContent(String tContent) {
        this.tContent = tContent;
    }

    public String getExampleUrl() {
        return exampleUrl;
    }

    public void setExampleUrl(String exampleUrl) {
        this.exampleUrl = exampleUrl;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

}
