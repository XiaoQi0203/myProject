package com.baizhi.um.datasource;

/**
 * @Author SIMBA1949
 * @Date 2020/6/7 22:53
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，被标记的service方法去master数据库
 */
@Retention(RetentionPolicy.RUNTIME) //可以再运行时读取到注解
@Target(ElementType.METHOD) //元注解：这个自定义注解可以标记在方法上
public @interface MasterDB {
}
