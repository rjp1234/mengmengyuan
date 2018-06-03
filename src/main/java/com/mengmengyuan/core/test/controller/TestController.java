/**    
 * 文件名：TestController.java    
 *    
 * 版本信息：    
 * 日期：2018年5月31日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.core.test.controller;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mengmengyuan.MengmengyuanApplication;
import com.mengmengyuan.common.util.redis.RedisUtils;
import com.mengmengyuan.core.base.BaseController;
import com.mengmengyuan.core.test.entity.TestEntity;
import com.mengmengyuan.core.test.service.TestService;

/**
 * 
 * 项目名称：mengmengyuan 类名称：TestController 类描述： 创建人：Administrator 创建时间：2018年5月31日
 * 下午9:50:18 修改人：Administrator 修改时间：2018年5月31日 下午9:50:18 修改备注：
 * 
 * @version
 * 
 */
@Controller
public class TestController extends BaseController {
    @Autowired
    TestService testService;

    @Autowired
    RedisUtils redisUtils;

    @RequestMapping("test-mysql")
    @ResponseBody
    public String testMysql() {
        System.out.println(testService);
        System.out.println(redisUtils);
        TestEntity test = new TestEntity();
        test.setId("12345");
        test.setName("name");
        // redisUtils.add("test", System.currentTimeMillis());
        // System.err.println(redisUtils.get("test"));
        System.out.println(testService.insert(test));
        System.out.println(testService.select(test));
        System.out.println(testService.delete());
        return "hello mysql";
    }

    @RequestMapping("test-redis")
    @ResponseBody
    public String testRedis() {
        redisUtils.set("test", "123");
        System.out.println(redisUtils.get("test"));
        redisUtils.remove("test");
        for (int i = 0; i < 10; i++) {
            redisUtils.lPush("list-test", i);
        }
        System.out.println(redisUtils.lRange("list-test", 0, -1));
        redisUtils.remove("list-test");
        return "hello redis";
    }

    public static void main(String[] args) {
        run(MengmengyuanApplication.class, args);
    }

}
