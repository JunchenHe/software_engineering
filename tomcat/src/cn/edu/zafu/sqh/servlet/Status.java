package cn.edu.zafu.sqh.servlet;

import cn.edu.zafu.sqh.bean.UserBean;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Status extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        UserBean user = (UserBean) session.getAttribute("user");
        if(user == null){
            user = new UserBean();
            session.setAttribute("user",user);
        }

        Gson g = new Gson();
        String json = g.toJson(user,UserBean.class);
        out.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
