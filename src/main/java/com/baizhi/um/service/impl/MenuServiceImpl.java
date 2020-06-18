package com.baizhi.um.service.impl;

import com.baizhi.um.dao.MenuDao;
import com.baizhi.um.entity.Menu;
import com.baizhi.um.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author SIMBA1949
 * @Date 2020/6/11 17:08
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> queryMenuAll() {
        return menuDao.queryMenuAll();
    }
}
