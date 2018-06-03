package com.mengmengyuan.mengmengyuan;

import java.sql.DriverManager;
import java.util.Base64;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mengmengyuan.common.util.redis.RedisUtils;
import com.mengmengyuan.core.test.dao.TestDao;
import com.mengmengyuan.core.test.entity.TestEntity;

/**
 * 
 * 项目名称：JeeSite 类名称：TestEnvironment 类描述： 基础运行环境测试 创建人：Administrator
 * 创建时间：2018年4月21日 下午7:23:08 修改人：Administrator 修改时间：2018年4月21日 下午7:23:08 修改备注：
 * 
 * @version
 * 
 */
public class TestEnvironment extends BaseTest {
    @Autowired
    public RedisUtils redisUtils;

    @Autowired
    TestDao testDao;

    @org.junit.Test
    public void mysqlConnectTest() throws Exception {
        String url = "jdbc:mysql://192.168.40.129:3306/mengmengyuan?useUnicode=true&characterEncoding=utf-8"; // 连接数据库（kucun是数据库名）

        String name = "root";// 连接mysql的用户名

        String pwd = "123";// 连接mysql的密码
        Class.forName("com.mysql.jdbc.Driver");
        java.sql.Connection conn = DriverManager.getConnection(url, name, pwd);
        System.out.println(conn);
    }

    @Test
    public void mysqlInsertTest() {
        TestEntity test = new TestEntity();
        test.setId("123");
        test.setName("name");
        testDao.insert(test);
        testDao.select(test);
        System.out.println(testDao.select(test));
        testDao.deleteAll();
    }

    @Test
    public void JedisTest() {
        // redisUtils.add("renjianping", "test");
        // System.out.println(redisUtils.get("renjianping"));
    }

    // @Test
    // public void zookeeperTest() throws IOException, KeeperException,
    // InterruptedException {
    // String hostPort = "192.168.40.129:2181";
    // String zpath = "/";
    // List<String> zooChildren = new ArrayList<String>();
    // ZooKeeper zk = new ZooKeeper(hostPort, 2000, null);
    // if (zk != null) {
    // try {
    // zooChildren = zk.getChildren(zpath, false);
    // for (String child : zooChildren) {
    // // print the children
    // System.out.println(child);
    // }
    // } catch (KeeperException e) {
    // e.printStackTrace();
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    //
    // }
    // }

    public static void main(String[] args) {
        System.out.println("renjianping".getBytes());
        byte[] encode = Base64.getEncoder().encode("renjianping".getBytes());
        System.out.println(encode);

    }

}
