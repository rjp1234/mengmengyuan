package com.mengmengyuan.common.util.redis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

/**
 * 当前运行环境不足以同时运行redis及mysql。redis暂时用内存读写代替
 */
@Service
public class RedisUtils {
    private static final Map<String, Map<String, Object>> redisMap = new ConcurrentHashMap<String, Map<String, Object>>();

    /**
     * 哈希 添加
     * 
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, String hashKey, Object value) {
        if (redisMap.containsKey(key)) {
            redisMap.get(key).put(hashKey, value);
        } else {
            Map<String, Object> hashMap = new ConcurrentHashMap<String, Object>();
            hashMap.put(hashKey, value);
            redisMap.put(key, hashMap);
        }

    }

    /**
     * 哈希获取数据
     * 
     * @param key
     * @param hashKey
     * @return key或hashkey不存在时返回null
     */
    public Object hmGet(String key, String hashKey) {
        if (redisMap.containsKey(key)) {
            return redisMap.get(key).get(hashKey);
        } else {
            return null;
        }

    }

    /**
     * 
     * hmHasKey(判断hashKey是否在hash表中存在)
     * 
     */
    public boolean hmHasKey(String key, Object hashKey) {
        if (redisMap.containsKey(key)) {
            return redisMap.get(key).containsKey(hashKey);

        }
        return false;
    }

}