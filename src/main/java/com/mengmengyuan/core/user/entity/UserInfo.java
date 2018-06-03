/**    
 * 文件名：UserLoginInfo.java    
 *    
 * 版本信息：    
 * 日期：2018年6月1日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.user.entity;

import java.sql.Date;

import com.mengmengyuan.core.base.BaseEntity;

/**
 * 
 * 项目名称：mengmengyuan 类名称：UserLoginInfo 类描述： 创建人：Administrator 创建时间：2018年6月1日
 * 下午5:04:51 修改人：Administrator 修改时间：2018年6月1日 下午5:04:51 修改备注： 用户登录类
 * 
 * @version数据库传输类
 * 
 * 
 */
public class UserInfo extends BaseEntity {

    private String password;// 用户密码

    private String accToken;// 用户登录通行证

    private String loginname;// 登录名

    private Date loginDate;// 最后登录时间

    private String phonenum;// 手机号

    private String nickname;// 昵称

    private String image;// 用户头像

    private int userType;// 用户类型

    private Date createDate;// 用户创建时间

    public static final int USER_TYPE_LOCAL_STUDENT = 0;// 用户类型为本地学生

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginName(String loginName) {
        this.loginname = loginName;
    }

    public String getAccToken() {
        return accToken;
    }

    public void setAccToken(String accToken) {
        this.accToken = accToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
