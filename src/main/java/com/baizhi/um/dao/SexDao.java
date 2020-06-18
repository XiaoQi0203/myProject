package com.baizhi.um.dao;


import com.baizhi.um.entity.Sex;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author SIMBA1949
 * @Date 2020/6/13 16:47
 */
@Repository
public interface SexDao {
    List<Sex> selectCount();
}
