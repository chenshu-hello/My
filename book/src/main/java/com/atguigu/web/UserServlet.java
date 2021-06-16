package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @title: UserServlet
 * @Author ChenShu
 * @Date: 2021/6/4 17:35
 * @Version 1.0
 */
public class UserServlet extends BaseServlet  {

    private UserService userService=new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loginUser = userService.login(new User(null, username, password, null));

        req.setAttribute("msg","用户名密码错误");
        req.setAttribute("username",username);
        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            req.getSession().setAttribute("user",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getSession().invalidate();

            resp.sendRedirect(req.getContextPath());

    }
        protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String attribute = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String email=req.getParameter("email");
        String code=req.getParameter("code");


//        Map<String,String[]> parameterMap=req.getParameterMap();
//        for (Map.Entry<String,String[]> entry:parameterMap.entrySet()){
//            System.out.println(entry.getKey()+"="+ Arrays.asList(entry.getValue()));
//        }

        User user= (User) WebUtils.copyParamToBean(req.getParameterMap(),new User());
        //2、检查 验证码是否正确===写死，要求验证码：abcde
        if (attribute!=null&&attribute.equalsIgnoreCase(code)){
            if (userService.existUsername(username)){
                System.out.println("用户名["+username+"]已存在");
                req.setAttribute("msg","验证码错误！！");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //保存到数据库
                userService.registUser(new User(null,username,password,email));

                //跳转到成功注册页面当中
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {

            req.setAttribute("msg","验证码错误！！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            System.out.println("验证码["+code+"]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
}
