package com.baizhi.um.cache;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * @Author SIMBA1949
 * @Date 2020/6/3 6:47
 */
public class RedisChche implements Cache {
    //namespace
    private String id;

    private static RedisTemplate redisTemplate = (RedisTemplate) MyApplicationContextAware.getBean("redisTempalte");
    //获取id
    public RedisChche(String id){
        this.id = id;
    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        redisTemplate.opsForHash().put(id,key,value);
    }

    @Override
    public Object getObject(Object key) {
        return redisTemplate.opsForHash().get(id,key);
    }

    @Override
    public Object removeObject(Object key) {
        return redisTemplate.opsForHash().delete(id,key);
    }

    @Override
    public void clear() {
        redisTemplate.delete(id);
    }

    @Override
    public int getSize() {
        long size = redisTemplate.opsForHash().size(id);
        return (int) size;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
