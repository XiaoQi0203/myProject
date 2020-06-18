package com.baizhi.um.controller;

import com.baizhi.um.entity.Menu;
import com.baizhi.um.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author SIMBA1949
 * @Date 2020/6/11 21:25
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/queryMenuAll")
    public List queryMenuAll(){
        List<Menu> menus = menuService.queryMenuAll();
        return menus;
    }
}
