package com.baizhi.um.datasource;

/**
 * @Author SIMBA1949
 * @Date 2020/6/7 22:58
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义切面，在这个切面需要读取service方法上注解
 * 决定dao操作是用哪个数据源
 */
@Aspect  //标记这个类是切面
@Component  //创建对象：由spring工厂创建对象
@Order(Ordered.HIGHEST_PRECEDENCE)  //指定这个切面的执行级别，值越低执行优先级越高
public class DBSelectAspect {



    /**
     * @Around():标记是一个环绕增强
     * @param proceedingJoinPoint 出来连接点：可以获取目录相关的信息，目标对象、目标方法
     * @return
     */
    @Around("execution(* com.baizhi.um.service.*.*(..))")
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //1.读取目标方法上的注解，如果有则读取master；没有则选择slave
        //2.调用目标方法
        //1.1通过连接点对象获取签名，方法签名
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //1.2获取方法
        Method method = signature.getMethod();
        //1.3判读方法上是否有指定注解
        boolean annotationPresent = method.isAnnotationPresent(MasterDB.class);
        OperationType operationType = OperationType.READ;
        if (annotationPresent){
            operationType = OperationType.WRITE;
        }
        //设置操作类型到ThreadLocal
        OperationTypeContextHolder.setOperationType(operationType);

        Object obj = null;
        try {
            //2.执行目标方法
            obj = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw  throwable; //如果目标方法异常处理掉了，声明式事务处理就失效了
        }finally {
            OperationTypeContextHolder.clear();
        }
        return obj;
    }
}
