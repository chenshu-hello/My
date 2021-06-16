package com.atguigu.web;

import com.alibaba.druid.support.http.stat.WebAppStatUtils;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @title: BookServlet
 * @Author ChenShu
 * @Date: 2021/6/5 14:25
 * @Version 1.0
 */
public class BookServlet extends BaseServlet {

    private BookService bookService=new BookServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> books = bookService.queryBooks();

        req.setAttribute("books",books);

        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }


    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        //获取请求的参数==封装成Book对象
        Book book= (Book) WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //调用BookService.addBook()保存图书
        bookService.addBook(book);
        //跳到图书列表页面
        //使用重定向
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      int id=WebUtils.parseInt(req.getParameter("id"),0);

      bookService.deleteBookById(id);

      resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=WebUtils.parseInt(req.getParameter("id"),0);

        Book book = bookService.queryBookById(id);

        req.setAttribute("book",book);

        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book= (Book) WebUtils.copyParamToBean(req.getParameterMap(),new Book());

        bookService.updateBook(book);

        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           int pageNo =WebUtils.parseInt(req.getParameter("pageNo"),1);
           int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

           Page<Book> page=bookService.page(pageNo,pageSize);
           page.setUrl("manager/bookServlet?action=page");


           req.setAttribute("page",page);

           req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
