package com.baizhi.um.datasource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author SIMBA1949
 * @Date 2020/6/7 21:37
 */

/**
 * 1.创建三个数据源：主机和两个丛集
 * 2.需要吧springboot原有的SQLSessionFactory对象覆盖
 * 3.需要吧springboot中原有的sqlSessionTempate对象覆盖
 * 4.需要爸爸springboot原有的事务管理器 对象覆盖掉
 */
//@Configuration  //配置注解，让spring工厂管理这个类
public class MyDataSource {
 /*   *//**
     * 主机对应数据源
     * @return
     *//*
    @Bean("masterDataSourece")  //把方法返回值讲给spring工厂管理，在工厂中的名字masterDataSourece
    @ConfigurationProperties("spring.datasource.master")    //到springboot配置文件中，根据指定key读取数据源需要的参数
    public DataSource getMasterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("slave1DataSource")
    @ConfigurationProperties("spring.datasource.slave1")
    public DataSource getSlave1DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("slave2DataSource")
    @ConfigurationProperties("spring.datasource.slave2")
    public DataSource getSlave2DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("myRoutingDataSource")
    public MyRoutingDataSource getMyRoutingDataSource(DataSource masterDataSourece,DataSource slave1DataSource,DataSource slave2DataSource){
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        //所有可用的数据源
        Map<Object,Object> targetDataSources = new HashMap<>();
        targetDataSources.put(Constants.MASTER,masterDataSourece);
        targetDataSources.put(Constants.SLAVE1,slave1DataSource);
        targetDataSources.put(Constants.SLAVE2,slave2DataSource);

        myRoutingDataSource.setTargetDataSources(targetDataSources);
        //默认数据源
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSourece);
        return myRoutingDataSource;
    }


    //@Qualifier("masterDataSourece"):按名称自动装配
    @Bean("sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("myRoutingDataSource") DataSource dataSource) throws Exception {
        //创建sqlsessionFactorBean对象，用来创建SQLSessionFactory对象
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        //指定包实体类起别名
        sqlSessionFactoryBean.setTypeAliasesPackage("com.baizhi.FinancialRiskControlSystem.entity");
        //加载映射文件
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/baizhi/FinancialRiskControlSystem/dao/*Mapper.xml"));

        //创建SQLSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

        return sqlSessionFactory;
    }

    @Bean("sqlSessionTemplate")
    public SqlSessionTemplate getSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        //通过SQLSessionFactory对象创建SQLSessionTemplate，同时指定执行器类型：批处理
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
        return sqlSessionTemplate;
    }

    //创建事务管理对象，讲给spring工厂管理，吧springboot提供的事务管理器覆盖
    @Bean("transactionManager")
    public PlatformTransactionManager getTransactionManager(@Qualifier("myRoutingDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }*/
}
