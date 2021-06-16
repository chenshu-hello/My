package com.atguigu.web;

import com.atguigu.dao.impl.BaseDao;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @title: OrderServlet
 * @Author ChenShu
 * @Date: 2021/6/14 8:39
 * @Version 1.0
 */
public class OrderServlet extends BaseServlet {

    private OrderService orderService=new OrderServiceImpl();


    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先获取购物车
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        //获取Userid
        User loginUser= (User) req.getSession().getAttribute("user");

        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId=loginUser.getId();

        String orderId=orderService.createOrder(cart,userId);

        req.getSession().setAttribute("orderId",orderId);


        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    protected void myOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loginUser= (User) req.getSession().getAttribute("user");

        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId=loginUser.getId();

        List<Order> orders=orderService.myOrders(userId);

        req.getSession().setAttribute("orders",orders);

        resp.sendRedirect(req.getContextPath()+"/pages/order/order.jsp");
    }


    protected void orderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session域中的orderId
        String orderId=req.getParameter("orderId");

        Order order=orderService.orderDetails(orderId);

        req.getSession().setAttribute("order",order);

        resp.sendRedirect(req.getContextPath()+"/pages/order/order.jsp");

    }


    protected void allOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> allOrders=orderService.allOrders();

        req.getSession().setAttribute("allOrders",allOrders);

        resp.sendRedirect(req.getContextPath()+"/pages/order/order.jsp");
    }
}
