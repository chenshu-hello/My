package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @title: OrderDaoTest
 * @Author ChenShu
 * @Date: 2021/6/13 20:16
 * @Version 1.0
 */
public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();

        orderDao.saveOrder(new Order("1234567891", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), new BigDecimal(100), 0, 1));
    }


}
