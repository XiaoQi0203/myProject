package com.baizhi.um.cache;

import org.apache.ibatis.cache.Cache;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * @Author SIMBA1949
 * @Date 2020/6/3 5:29
 */
public class MybatisChche implements Cache {

    @Override   //获取缓存编号
    public String getId() {
        return getId();
    }

    @Override   //保存key值缓存对象
    public void putObject(Object key, Object value) {

    }

    @Override   //通过key获取缓存对象
    public Object getObject(Object key) {
        return null;
    }

    @Override   //通过key删除缓存对象
    public Object removeObject(Object key) {
        return null;
    }

    @Override   //清空缓存
    public void clear() {

    }

    @Override   //获取缓存对象的大小
    public int getSize() {
        return 0;
    }

    @Override   //获取缓存的读写锁
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
