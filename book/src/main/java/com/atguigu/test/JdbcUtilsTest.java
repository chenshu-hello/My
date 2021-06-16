package com.atguigu.test;

import com.atguigu.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @title: JdbcUtilsTest
 * @Author ChenShu
 * @Date: 2021/6/1 11:57
 * @Version 1.0
 */
public class JdbcUtilsTest {

    @Test
      public void testJdbcUtils(){
        for (int  i=0;i<10;i++){
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.close(connection);
        }
    }
}
