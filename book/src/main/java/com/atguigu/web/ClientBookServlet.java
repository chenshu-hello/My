package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: ClientBookServlet
 * @Author ChenShu
 * @Date: 2021/6/10 9:44
 * @Version 1.0
 */
public class ClientBookServlet extends BaseServlet{

     private BookService bookService=new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("经过前台的访问");
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Page<Book> page=bookService.page(pageNo,pageSize);

        page.setUrl("client/bookServlet?action=page");

        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min=WebUtils.parseInt(req.getParameter("min"),0);
        int max=WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);

        Page<Book> page=bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder sb=new StringBuilder("client/bookServlet?action=pageByPrice");

        if (req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());

        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
