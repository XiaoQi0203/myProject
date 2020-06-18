package com.baizhi.um.dao;

import com.baizhi.um.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author SIMBA1949
 * @Date 2020/6/1 5:39
 */
public interface UserDao {
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

    /**
     * 给定字段做分页查询
     * @param offset
     * @param limit
     * @param column 查询需要的字段
     * @param value 对应字段数据
     * @return
     */
    List<User> queryUserByPage(
        @Param(value = "offset") Integer offset,
        @Param(value = "limit") Integer limit,
        @Param(value = "column") String column,
        @Param(value = "value") Object value
    );

    /**
     * 查询条数
     * @param column
     * @param value
     * @return
     */
    int queryCount(
        @Param(value = "column") String column,
        @Param(value = "value") Object value
    );

    /**
     * 查询全部
     */
    List<User> selectUserAll();

    /**
     * 分页查询
     * @param offset
     * @param limit
     * @return
     */
    List<User> selectUserByPage(Integer offset,Integer limit);
    //修改
    void updateUser(User user);
}
