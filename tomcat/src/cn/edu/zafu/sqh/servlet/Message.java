package cn.edu.zafu.sqh.servlet;

/**
 * 接收一个参数 timestamp
 * 查看session中的登录状态和用户等级
 * 返回对应用户等级的信息
 */

import cn.edu.zafu.sqh.bean.MessageBean;
import cn.edu.zafu.sqh.bean.UserBean;
import cn.edu.zafu.sqh.util.JDBCUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Message extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type","text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String time = req.getParameter("time");

        HttpSession session = req.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if(user == null){
            user = new UserBean();
            session.setAttribute("user",user);
            return;
        }

        //登录参数不全，直接返回
        if(!user.isLogin() || time == null || time.length() == 0){
            out.println("request error,may not login,may no parameter");
            return;
        }



        JDBCUtil util = new JDBCUtil();

        MessageBean message = util.getMessage(time,user.userid);

        util.disconnect();
        if(message != null)
            out.print(message.getNote());
        else {
            out.println("无此信息");
        }
    }
}
