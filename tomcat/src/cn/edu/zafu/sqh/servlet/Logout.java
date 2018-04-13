package cn.edu.zafu.sqh.servlet;
/**
 * 用于退出登录
 * session中如果登录状态为已登录，则取消登录状态
 * 其他情况忽略
 */

import cn.edu.zafu.sqh.bean.UserBean;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class Logout extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if(user == null){
            user = new UserBean();
            session.setAttribute("user",user);
        }
        user.setLogin(false);
    }
}
