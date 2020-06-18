package com.baizhi.um.cache;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @Author SIMBA1949
 * @Date 2020/6/3 6:44
 */
//@Configuration
public class MyApplicationContextAware implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    //
    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }
}
