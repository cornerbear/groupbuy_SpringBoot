package com.xj.groupbuy;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
class GroupbuyApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    
    @Test
    void encoderPwd() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));

//        Integer integer = jdbcTemplate.queryForObject("select count(*) from ttest", Integer.class);
//        log.info("记录总数: {}",integer);
//
//        log.info("shujuyuanleixing: {}",dataSource.getClass());

    }
//    
//    @Test
//    void testRedis(){
//        ValueOperations<String, String> operations = redisTemplate.opsForValue();
//        
//        operations.set("hello","world");
//
//        String hello = operations.get("hello");
//        System.out.println(hello);
//    }
//    
//    @Test
//    void testSqlSession(){
//        System.out.println(sqlSessionFactory.getClass());
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        
//        System.out.println();
//    }

}
