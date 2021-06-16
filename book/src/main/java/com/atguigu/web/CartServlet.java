package com.atguigu.web;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: CartServlet
 * @Author ChenShu
 * @Date: 2021/6/13 13:50
 * @Version 1.0
 */
public class CartServlet extends BaseServlet{

    private BookService bookService=new BookServiceImpl();
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        int id= WebUtils.parseInt(req.getParameter("id"),0);
        // 调用 bookService.queryBookById(id):Book 得到图书的信息
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为 CartItem 商品项
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 调用 Cart.addItem(CartItem);添加商品项
        //此添加商品的方法原理是将商品对象添加到Session
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);
        //为了添加完购物车后将页面跳转到原先的页面而不是主页使用请求头来确定网页的位置
        System.out.println("请求头Referer的值"+req.getHeader("Referer"));
        // 重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));

        req.getSession().setAttribute("lastname",cartItem.getName());
    }


    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id= WebUtils.parseInt(req.getParameter("id"),0);

        Cart cart= (Cart) req.getSession().getAttribute("cart");

        if (cart!=null){
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }


    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart= (Cart) req.getSession().getAttribute("cart");

        if (cart!=null){
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }


    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        int count=WebUtils.parseInt(req.getParameter("count"),1);

        Cart cart= (Cart) req.getSession().getAttribute("cart");

        if (cart!=null){
            cart.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
