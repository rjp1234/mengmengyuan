package com.mengmengyuan.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class Global {
    private static Properties properties = null;

    private static Map<String, String> configMap = new ConcurrentHashMap<String, String>();

    public static String getConfig(String key) {
        if (properties == null) {
            synchronized (Global.class) {
                if (properties == null) {
                    Properties properties = new Properties();
                    // 使用ClassLoader加载properties配置文件生成对应的输入流
                    InputStream in = Global.class.getClassLoader().getResourceAsStream("application.properties");
                    // 使用properties对象加载输入流
                    try {
                        properties.load(in);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    Global.properties = properties;
                }
            }
        }
        if (configMap.containsKey(key)) {
            return configMap.get(key);
        } else {
            String value = properties.getProperty(key);
            if (value == null) {
                return null;
            }
            configMap.put(key, value);
            return value;
        }

    }
}
