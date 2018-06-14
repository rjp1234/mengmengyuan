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

/**
 * 
 * 项目名称：mengmengyuan 类名称：LessionPageInfo 类描述： 创建人：Administrator 创建时间：2018年6月14日
 * 下午5:19:20 修改人：Administrator 修改时间：2018年6月14日 下午5:19:20 修改备注：
 * 
 * @version 用于返回首页面的课程列表
 */
public class LessionPageInfo {
    private String id;// 课文id

    private String name;// 课文名称

    private String image;// 课文封面

    private String createTime;// 创建时间

    private int readNum;// 总朗读人数

    private int reciteNum;// 总背诵人数

    private int state;// 当前用户状态 0\未进行。1、已朗读。2、朗读+背诵

    public static final int STATE_BLANK = 0;

    public static final int STATE_READ = 1;

    public static final int STATE_RECITE = 2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
