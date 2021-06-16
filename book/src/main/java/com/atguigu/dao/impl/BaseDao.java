package com.atguigu.dao.impl;

import com.atguigu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @title: BaseDao
 * @Author ChenShu
 * @Date: 2021/6/1 22:06
 * @Version 1.0
 */
public class BaseDao {

    //update() 方法用来执行：Insert\Update\Delete语句

    //return 如果返回-1，说明执行失败<br/> 返回其他表示影响行数
       public int update(String sql,Object... args){
           Connection connection = JdbcUtils.getConnection();

           try {
               QueryRunner queryRunner = new QueryRunner();

               return queryRunner.update(connection,sql,args);
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               JdbcUtils.close(connection);
           }

           return -1;
       }

       //查询返回一个javaBean 的sql语句
       //type 返回对象的类型
       //sql  执行的sql语句
       //args args sql对象的类型的泛型
       //
       //

       public <T>  T queryForOne(Class<T> type,String sql,Object... args){
           Connection conn = JdbcUtils.getConnection();

           try {
               QueryRunner queryRunner = new QueryRunner();

               return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               JdbcUtils.close(conn);
           }
           return null;
       }

       //查询返回多个javaBean的sql语句
       //其中的方法函数将返回的结果集转化为数组然后放入list中

    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection con = JdbcUtils.getConnection();
        try {
            QueryRunner queryRunner = new QueryRunner();
            return queryRunner.query(con, sql, new BeanListHandler<T>(type), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(con);
        }
        return null;
    }

    //执行返回一行一列执行的sql语句
    //sql 执行的sql语句
    //args sql对应的参数值
    //
    public Object queryForSingleValue(String sql,Object...  args){

        Connection conn = JdbcUtils.getConnection();

        try {
            QueryRunner queryRunner = new QueryRunner();
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn);
        }

        return null;

    }





}
