package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;

public interface OrderDao {
    public int saveOrder(Order order);

    public List<Order> queryMyOrders(Integer userId);


    Order queryOrderDetailById(String orderId);

    public List<Order> queryAllOrders();
}
