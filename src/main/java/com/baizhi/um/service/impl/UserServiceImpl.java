package com.baizhi.um.service.impl;

import com.baizhi.um.dao.UserDao;
import com.baizhi.um.datasource.MasterDB;
import com.baizhi.um.entity.User;
import com.baizhi.um.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author SIMBA1949
 * @Date 2020/6/1 5:40
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /*@Autowired
    private RedisTemplate redisTemplate;*/

    @Override
    @MasterDB
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User queryUserByNameAndPassword(User user) {

        return userDao.queryUserByNameAndPassword(user);
    }

    @Override
    public User queryUserById(Integer id) {

        return userDao.queryUserById(id);
    }

    @Override
    @MasterDB
    public void deleteByUserId(Integer id) {

        userDao.deleteByUserId(id);
    }

    @Override
    @MasterDB
    public void deleteByUserIds(Integer[] ids) {

        userDao.deleteByUserIds(ids);
    }

    @Override
    public Map queryUserByPage(Integer pageIndex, Integer pageSize, String column, Object value) {
        /*String key = "queryUserByPage"+pageIndex+"limit"+pageIndex+"value"+value;
        Object redisValue = redisTemplate.opsForValue().get(key);
        List<User> list = null;
        if (redisValue!=null){
            list = (List<User>) redisValue;
        }
        if (list==null){
            list = userDao.queryUserByPage((pageIndex - 1) * pageSize, pageSize, column, value);
            redisTemplate.opsForValue().set(key,list);
        }*/
        Integer offset = (pageIndex - 1) * pageSize;
        List<User> list = userDao.queryUserByPage(offset, pageSize, column, value);
        /*for (User user : list) {
            System.out.println(user);
        }*/
        /*System.out.println("offset"+offset);
        System.out.println("pageIndex"+pageIndex);
        System.out.println("pageSize"+pageSize);
        System.out.println("============end===========");*/
        int count = userDao.queryCount(column, value);
        Map map = new HashMap<>();
        map.put("rows", list);
        map.put("total", count);
        return map;
    }

    @Override
    public int queryCount(String column, Object value) {

        return userDao.queryCount(column, value);
    }
    @Override
    public List<User> selectUserAll(){
        List<User> users = userDao.selectUserAll();
        String url = "http://FastDFS/";
        for (User user : users) {
            String photo = user.getPhoto();
            String[] split = photo.split("\\*");
            user.setPhoto(url+split[0]);
        }
        return users;
    }
    @Override
    public List<User> selectUserByPage(Integer pageIndex,Integer rows){
        List<User> users = userDao.selectUserByPage(pageIndex, rows);
        String url = "http://FastDFS/";
        for (User user : users) {
            String photo = user.getPhoto();
            String[] split = photo.split("\\*");
            user.setPhoto(url+split[0]);
        }
        return users;
    }
    @Override
    @MasterDB
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
