package cn.edu.zafu.sqh.servlet;

/**
 * 用于登录的servlet
 * 以get方式获得两个参数
 * 参数1：username
 * 参数2：password
 * 后台进行登录验证后将登录信息存储到session中
 * 返回的值有两种：
 * 1.返回用户ID表示登陆成功
 * 2.返回-1表示登录失败
 */

import cn.edu.zafu.sqh.bean.UserBean;
import cn.edu.zafu.sqh.util.JDBCUtil;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //test Gson dependency
        //Gson g = new Gson();
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if(user == null){
            user = new UserBean();
            session.setAttribute("user",user);
        }

        //登录参数不全，直接返回
        if(username == null || password == null){
            out.print(-1);
            return;
        }

        if(user.isLogin()){
            user.setLogin(false);
            return;
        }

        JDBCUtil util = new JDBCUtil();
        util.login(username,password,user);

        util.disconnect();
        user.setUsername(username);
        if(user.isLogin()){
            out.print(user.getUserid());
        }else{
            out.print(-1);
        }
    }
}
