package com.xj.groupbuy.common.config;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/24
 */

@Configuration
public class TransactionConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * 向容器中注入事务管理器
     * 注意，要给该bean取一个名字，名字不要是transactionManager，否则会报错
     */
    @Bean("transactionManager")
    public TransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 向容器中注入TransactionInterceptor,
     * TransactionInterceptor会完成事务切面的逻辑
     * 注意，要给该bean取一个名字，名字不要是transactionInterceptor，否则会报错
     */
    @Bean("txAdvice")
    public TransactionInterceptor transactionInterceptor(TransactionManager transactionManager){
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

        //非只读事务
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        //只读事务
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);

        //配置加事务的规则,没有匹配到的方法将不会有事务，这些方法指的是Pointcut匹配到的方法
        Map<String, TransactionAttribute> map = new HashMap<>();
        map.put("add*", requiredTx);//Pointcut匹配到的方法中所有add开头的方法
        map.put("save*", requiredTx);//Pointcut匹配到的方法中所有save开头的方法
        map.put("create*", requiredTx);//Pointcut匹配到的方法中所有save开头的方法
        map.put("insert*", requiredTx);
        map.put("update*", requiredTx);
        map.put("delete*", requiredTx);
        map.put("select*", readOnlyTx);
        map.put("get*", readOnlyTx);
        source.setNameMap(map);
        return new TransactionInterceptor(transactionManager, source);
    }


    /**
     * 向容器中注入切入点
     */
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(TransactionInterceptor txAdvice){
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice(txAdvice);
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution (* com.xj.groupbuy.service.impl.*.*(..))");
        advisor.setPointcut(pointcut);
        return advisor;
    }
}
