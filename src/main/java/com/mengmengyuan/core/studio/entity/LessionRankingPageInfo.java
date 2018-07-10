/**    
 * 文件名：LessionRankingPageInfo.java    
 *    
 * 版本信息：    
 * 日期：2018年7月10日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.studio.entity;

import com.mengmengyuan.core.base.BaseEntity;

/**
 * 
 * 项目名称：mengmengyuan 类名称：LessionRankingPageInfo 类描述： 创建人：Administrator
 * 创建时间：2018年7月10日 下午1:15:55 修改人：Administrator 修改时间：2018年7月10日 下午1:15:55 修改备注：
 * 
 * @version
 * 
 */
public class LessionRankingPageInfo extends BaseEntity {
    private String userId;// 用户id

    private String userName;// 用户姓名（真实姓名）

    private String lessionId;// 课文id

    private String lessionName;// 课文名称

    private String studioUrl;// 录音路径

    private int ranking;// 排名

    private int point;// 分数

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLessionId() {
        return lessionId;
    }

    public void setLessionId(String lessionId) {
        this.lessionId = lessionId;
    }

    public String getLessionName() {
        return lessionName;
    }

    public void setLessionName(String lessionName) {
        this.lessionName = lessionName;
    }

    public String getStudioUrl() {
        return studioUrl;
    }

    public void setStudioUrl(String studioUrl) {
        this.studioUrl = studioUrl;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

}
