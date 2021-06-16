package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart carty,Integer userId);

    List<Order> myOrders(Integer userId);

   Order orderDetails(String orderId);

   List<Order> allOrders();
}
