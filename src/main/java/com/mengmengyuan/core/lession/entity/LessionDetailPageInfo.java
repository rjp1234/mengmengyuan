/**    
 * 文件名：LessionDetailPageInfo.java    
 *    
 * 版本信息：    
 * 日期：2018年6月15日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.lession.entity;

/**
 * 
 * 项目名称：mengmengyuan 类名称：LessionDetailPageInfo 类描述： 创建人：Administrator
 * 创建时间：2018年6月15日 下午1:28:22 修改人：Administrator 修改时间：2018年6月15日 下午1:28:22 修改备注：
 * 
 * @version
 * 
 *          进入课文详细页面后的课文显示信息
 */
public class LessionDetailPageInfo {
    private String id;

    private String name;// 课文名称

    private String issueTime;// 下发时间

    private String content;// 课文文本

    private int completeNum;// 完成人数

    private String exampleUrl;// 示范录音url

    private String tContent;// 老师的话文本

    private String tStudioUrl;// 老师的话录音

    private int reciteState;// 背诵状态

    private int readState;// 朗读状态

    private String image;// 封面

    private String creater;

    public static final int STATE_COMPLETE = 1;// 状态完成

    public static final int STATE_UNFINISHED = 0;// 状态未完成

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(int completeNum) {
        this.completeNum = completeNum;
    }

    public String getExampleUrl() {
        return exampleUrl;
    }

    public void setExampleUrl(String exampleUrl) {
        this.exampleUrl = exampleUrl;
    }

    public String gettContent() {
        return tContent;
    }

    public void settContent(String tContent) {
        this.tContent = tContent;
    }

    public String gettStudioUrl() {
        return tStudioUrl;
    }

    public void settStudioUrl(String tStudioUrl) {
        this.tStudioUrl = tStudioUrl;
    }

    public int getReciteState() {
        return reciteState;
    }

    public void setReciteState(int reciteState) {
        this.reciteState = reciteState;
    }

    public int getReadState() {
        return readState;
    }

    public void setReadState(int readState) {
        this.readState = readState;
    }

}
