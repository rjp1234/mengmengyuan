/**    
 * 文件名：UserRankDetailPageInfo.java    
 *    
 * 版本信息：    
 * 日期：2018年7月8日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.user.entity;

import com.mengmengyuan.core.base.BaseEntity;

/**
 * 
 * 项目名称：mengmengyuan 类名称：UserRankDetailPageInfo 类描述： 创建人：Administrator
 * 创建时间：2018年7月8日 上午10:49:00 修改人：Administrator 修改时间：2018年7月8日 上午10:49:00 修改备注：
 * (A:8-10 P:4-7 E:0-3 )
 * 
 * @version 返回学生当前名次详情信息:
 * @0、用户id @1、 作业完成次数
 * @2、总分
 * @3、平均分
 * @4、APE分布
 * @5、名次
 * @6、班级总人数
 * @7、排行领先用户前一名学生姓名
 * 
 */
public class UserRankDetailPageInfo extends BaseEntity {
    private int completeNum;// 作业完成次数

    private String name;// 学生真实姓名

    private int issueNum;// 总下发到所在班级的作业数目(包括未打分的)

    private int sumPoint;// 总分

    private double avgPoint;// 平均分（不计算未打分的）

    private int aNum;// 获得a评分的次数

    private int pNum;// 获得p评分的次数

    private int eNum;// 获得e评分的次数

    private int ranking;// 当前排名

    private int classmateNum;// 所在班级总人数

    private String frontRunnerName;// 排行领先用户前一名学生姓名

    private String lastPointTime;// 最后一次打分时间

    /**
     * lastPointTime
     * 
     * @return the lastPointTime
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public String getLastPointTime() {
        return lastPointTime;
    }

    /**
     * @param lastPointTime
     *            the lastPointTime to set
     */
    public void setLastPointTime(String lastPointTime) {
        this.lastPointTime = lastPointTime;
    }

    /**
     * name
     * 
     * @return the name
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * issueNum
     * 
     * @return the issueNum
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public int getIssueNum() {
        return issueNum;
    }

    /**
     * @param issueNum
     *            the issueNum to set
     */
    public void setIssueNum(int issueNum) {
        this.issueNum = issueNum;
    }

    /**
     * completeNum
     * 
     * @return the completeNum
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public int getCompleteNum() {
        return completeNum;
    }

    /**
     * @param completeNum
     *            the completeNum to set
     */
    public void setCompleteNum(int completeNum) {
        this.completeNum = completeNum;
    }

    /**
     * sumPoint
     * 
     * @return the sumPoint
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public int getSumPoint() {
        return sumPoint;
    }

    /**
     * @param sumPoint
     *            the sumPoint to set
     */
    public void setSumPoint(int sumPoint) {
        this.sumPoint = sumPoint;
    }

    /**
     * avgPoint
     * 
     * @return the avgPoint
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public double getAvgPoint() {
        return avgPoint;
    }

    /**
     * @param avgPoint
     *            the avgPoint to set
     */
    public void setAvgPoint(double avgPoint) {
        this.avgPoint = avgPoint;
    }

    /**
     * aNum
     * 
     * @return the aNum
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public int getaNum() {
        return aNum;
    }

    /**
     * @param aNum
     *            the aNum to set
     */
    public void setaNum(int aNum) {
        this.aNum = aNum;
    }

    /**
     * pNum
     * 
     * @return the pNum
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public int getpNum() {
        return pNum;
    }

    /**
     * @param pNum
     *            the pNum to set
     */
    public void setpNum(int pNum) {
        this.pNum = pNum;
    }

    /**
     * eNum
     * 
     * @return the eNum
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public int geteNum() {
        return eNum;
    }

    /**
     * @param eNum
     *            the eNum to set
     */
    public void seteNum(int eNum) {
        this.eNum = eNum;
    }

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

    /**
     * classmateNum
     * 
     * @return the classmateNum
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public int getClassmateNum() {
        return classmateNum;
    }

    /**
     * @param classmateNum
     *            the classmateNum to set
     */
    public void setClassmateNum(int classmateNum) {
        this.classmateNum = classmateNum;
    }

    /**
     * frontRunnerName
     * 
     * @return the frontRunnerName
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public String getFrontRunnerName() {
        return frontRunnerName;
    }

    /**
     * @param frontRunnerName
     *            the frontRunnerName to set
     */
    public void setFrontRunnerName(String frontRunnerName) {
        this.frontRunnerName = frontRunnerName;
    }

}
