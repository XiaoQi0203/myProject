package com.baizhi.um.datasource;

/**
 * @Author SIMBA1949
 * @Date 2020/6/7 23:15
 */

/**
 * 设置操作类型：读操作|写操作
 */
public class OperationTypeContextHolder {
    private static final ThreadLocal<OperationType> OPERATION_TYPE_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置操作类型
     * @param operationType
     */
    public static void setOperationType(OperationType operationType){
        OPERATION_TYPE_THREAD_LOCAL.set(operationType);
    }

    public static OperationType getOperationType(){
        return OPERATION_TYPE_THREAD_LOCAL.get();
    }

    /**
     * 清除线程资源
     */
    public static void clear(){
        OPERATION_TYPE_THREAD_LOCAL.remove();
    }
}
