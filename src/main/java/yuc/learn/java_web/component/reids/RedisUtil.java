package yuc.learn.java_web.component.reids;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作工具类
 *
 * @author YuC
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /**
     * common
     */

    /**
     * 指定缓存失效时间
     *
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取key过期时间
     *
     * @param key 不能为null
     * @return 时间（秒）| 返回0代表永久有效
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return true存在 | false不存在
     */
    public boolean hasKey(String key) {
        try {
            // 使 redisTemplate.hasKey 返回的空值也变为 false
            return redisTemplate.hasKey(key) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public void delete(String... key) {
        if (key != null && key.length > 0) {
            redisTemplate.delete(new ArrayList<>(Arrays.asList(key)));
        }
    }


    /**
     * 对象转json工具
     *
     * @param object
     * @return
     */
    private String objectToJson(Object object) {
        if (object instanceof Integer ||
                object instanceof Long ||
                object instanceof Short ||
                object instanceof Byte ||
                object instanceof Float ||
                object instanceof Double ||
                object instanceof Boolean ||
                object instanceof Character ||
                object instanceof String) {
            return String.valueOf(object);
        } else {
            return JSON.toJSONString(object);
        }
    }

    /**
     * 数据结构: String
     */

    /**
     * 缓存获取
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        if (key == null) {
            return null;
        }
        String val = redisTemplate.opsForValue()
                .get(key);
        return JSONObject.parseObject(val, clazz);
    }

    /**
     * 缓存置入并设置时间
     *
     * @param key
     * @param object
     * @param expire
     * @return
     */
    public boolean set(String key, Object object, long expire) {
        try {
            redisTemplate.opsForValue()
                    .set(key, objectToJson(object), expire, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 缓存置入
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, objectToJson(value));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 当key不存在时，缓存置入
     *
     * @param key
     * @param value
     * @param expire
     * @param timeUnit
     * @return key存在时，返回false
     */
    public Boolean setnx(String key, Object value, long expire, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(key, objectToJson(value), expire, timeUnit);
    }

    public Boolean setnx(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, objectToJson(value));
    }


    /**
     * 递增
     *
     * @param key
     * @param by
     * @return
     */
    public Long increment(String key, long by) {
        if (by < 0) {
            throw new RuntimeException("递增值须大于0");
        }
        return redisTemplate.opsForValue().increment(key, by);
    }

    /**
     * 递减
     *
     * @param key
     * @param by
     * @return
     */
    public Long decrement(String key, long by) {
        if (by < 0) {
            throw new RuntimeException("递减值须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -by);
    }


}
