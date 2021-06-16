package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BaseDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;
import org.apache.commons.dbutils.QueryRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @title: OrderServiceImpl
 * @Author ChenShu
 * @Date: 2021/6/14 8:13
 * @Version 1.0
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();
    //创建订单
    @Override
    public String createOrder(Cart cart, Integer userId) {

        // 订单号===唯一性
        String orderId=System.currentTimeMillis()+""+userId;
        // 创建一个订单
        Order order = new Order(orderId,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),cart.getTotalPrice(),0,userId);
        // 保存订单
        orderDao.saveOrder(order);
        // 遍历购物车中每一个商品项转换成为订单项保存到数据库
        for(Map.Entry<Integer, CartItem>entry:cart.getItems().entrySet()){
            // 获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            // 转换为每一个订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            // 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            Book book=bookDao.queryBookById(cartItem.getId());

            book.setSales(book.getSales()+orderItem.getCount());
            book.setStock(book.getStock()-orderItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        // 清空购物
        return orderId;
    }

    @Override
    public List<Order> myOrders(Integer userId) {
        return orderDao.queryMyOrders(userId);
    }

    @Override
    public Order orderDetails(String orderId) {
        return orderDao.queryOrderDetailById(orderId);
    }

    @Override
    public List<Order> allOrders() {
        return orderDao.queryAllOrders();
    }

}
