package cn.edu.zafu.sqh.util;

import cn.edu.zafu.sqh.bean.MessageBean;
import cn.edu.zafu.sqh.bean.UserBean;
import cn.edu.zafu.sqh.servlet.Message;

import java.sql.*;

public class JDBCUtil {
    /**
     * 是否连接数据库
     */
    public boolean connected;

    private Connection connection;
    /**
     * 连接数据库
     * @return 是否成功
     */
    public JDBCUtil(){
        if(connect()){
            System.out.println("login success");
        }
    }

    public boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/data?useUnicode=true&characterEncoding=UTF-8";
            connection = DriverManager.getConnection(url,"root","toor");
            connected = true;
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 取消数据库的连接
     */
    public void disconnect() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connected = false;
        }
    }

    /**
     * 进行登录
     * @param username 用户名
     * @param password 密码
     * @param user 用于存储登录状态的bean
     * @return 是否登陆成功
     */
    public  boolean login(String username, String password, UserBean user) {

        String sql = "SELECT * FROM user WHERE username = ? AND passwd = ?";
        PreparedStatement statement;
        ResultSet rs;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            rs = statement.executeQuery();
            if(rs.next()){
                user.setUsername(rs.getString("username"));
                user.setUserid(rs.getInt("userid"));
                user.setLevel(rs.getInt("level"));
                user.setLogin(true);
                return true;
            }else {
                user.setLogin(false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public MessageBean getMessage(String time, int userid){
        String sql = "SELECT * FROM message,user" +
                " WHERE user.userid = ? and time = ? AND message.level = user.level";

        PreparedStatement statement;
        ResultSet rs;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userid);
            statement.setString(2,time);
            rs = statement.executeQuery();
            if(rs.next()){
                MessageBean message = new MessageBean();
                message.setNote(rs.getString("note"));
                return message;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}