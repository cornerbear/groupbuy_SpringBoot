package com.xj.groupbuy.common.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/2
 */
@Configuration
public class MyBatisConfig {

    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //设置请求的页面大于最大页后操作， true调回到首页，false继续请求默认false
        //paginationInterceptor.setOverFlow(false);
        //设置最大单页限制数量，默认580条，-1不受限制
        // paginationInterceptor.setLimit( 500 ) ;
        //开启count 的join优化,只针对部分Left join

        // 分页拦截器
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setOverflow(true);
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);

        return mybatisPlusInterceptor;
    }
    
//    @Primary
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("sysDataSource")DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        return bean.getObject();
//    }
    
}