package com.baizhi.um.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author SIMBA1949
 * @Date 2020/6/7 22:37
 */

public class MyRoutingDataSource extends AbstractRoutingDataSource {
    //构建一个集合，存储两个常亮
    private List<String> allSlaves = Arrays.asList(Constants.SLAVE1,Constants.SLAVE2);
    //自动递增
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    protected Object determineCurrentLookupKey() {
        OperationType operationType = OperationTypeContextHolder.getOperationType();
        if (operationType.equals(OperationType.WRITE)){
            return Constants.MASTER;
        }else {
            //获取atomicInteger，并做自增
            int integerValue = atomicInteger.getAndIncrement();
            if (integerValue<0){
                integerValue = 0;
                //如果获取到的小于0，就从0开始
                atomicInteger.set(0);
            }
            int index = integerValue%allSlaves.size();
            return allSlaves.get(index);
        }
    }
}
