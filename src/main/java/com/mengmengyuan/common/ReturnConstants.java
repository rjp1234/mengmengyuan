/**    
 * 文件名：ReturnConstants.java    
 *    
 * 版本信息：    
 * 日期：2018年6月2日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.common;

/**
 * 
 * 项目名称：mengmengyuan 类名称：ReturnConstants 类描述： 创建人：Administrator 创建时间：2018年6月2日
 * 上午11:09:45 修改人：Administrator 修改时间：2018年6月2日 上午11:09:45 修改备注：
 * 
 * @version
 * 
 */
public class ReturnConstants {
    /**
     * 用户模块相关错误码 1~20
     */
    // 登录名为空
    public static final String ERROR_LOGIN_NAME_EMPTY = "0001";

    // 密码为空
    public static final String ERROR_PASSWORD_EMPTY = "0002";

    // 用户账户密码错误
    public static final String ERROR_PASSWORD_WORNG = "0003";

    // accToken 无效
    public static final String ERROR_ACCTOKEN_INVALID = "0004";

    // 密码不合法(变更密码或者账号注册时)
    public static final String ERROR_PASSWORD_INVALID = "0005";

    // 请求参数不合法
    public static final String ERROR_PARAM_INVALID = "0006";

    // 用户id不合法
    public static final String ERROR_USERID_INVALID = "0007";

    /**
     * 21~40 课文模块
     */
    public static final String ERROR_LESSION_ID_INVALID = "0021";

    public static final String ERROR_LESSION_IS_NOT_ISSUE = "0022";

    /**
     * 41~60 录音模块
     */

    public static final String ERROR_STUDIO_UPLOAD_FAILED = "0041";// 录音文件上传失败

    public static final String ERROR_STUDIO_TYPE_INVALID = "0042";// 录音类型（朗读/背诵）不合法

    public static final String ERROR_SAME_TYPE_STUDIO_ALREADY_UPLOAD = "0043";// 相同类型的录音已经上传
}
