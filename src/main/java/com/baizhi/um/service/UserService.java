package com.baizhi.um.service;

import com.baizhi.um.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Author SIMBA1949
 * @Date 2020/6/1 5:40
 */
public interface UserService {
    //添加用户
    void saveUser(User user);
    //根据用户名和密码查询
    User queryUserByNameAndPassword(User user);
    //根据id查询
    User queryUserById(Integer id);
    //根据id删除
    void deleteByUserId(Integer id);
    //批量删除
    void deleteByUserIds(Integer[] ids);

    //查询信息
    Map queryUserByPage(Integer pageIndex, Integer pageSize,
                                String column, Object value);

    //查询条数
    int queryCount( String column, Object value);
    //查询全部
    List<User> selectUserAll();
    //分页查询
    List<User> selectUserByPage(Integer offset,Integer limit);
    //修改
    void updateUser(User user);
}
