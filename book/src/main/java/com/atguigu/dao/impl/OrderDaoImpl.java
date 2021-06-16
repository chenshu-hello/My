package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Order;

import java.util.List;

/**
 * @title: OrderDaoImpl
 * @Author ChenShu
 * @Date: 2021/6/13 20:01
 * @Version 1.0
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";

        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());

    }

    @Override
    public List<Order> queryMyOrders(Integer userId) {
        String sql="select * from t_order where `user_id`=?";

        return queryForList(Order.class,sql,userId);
    }

    @Override
    public Order queryOrderDetailById(String orderId) {
        String sql="select * from t_order where `order_id`=?";
        return queryForOne(Order.class,sql,orderId);
    }

    @Override
    public List<Order> queryAllOrders() {
        String sql="select * from t_order";
        return queryForList(Order.class,sql);
    }

}
