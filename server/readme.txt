使用c#开发窗口应用程序，管理MySQL服务器
连接服务器ubuntu mysql
1 ubuntu服务器开启mysql服务,service mysql start
2 创建用户，并授权
3 修改 /etc/mysql/mysql.conf/mysqld.cof文件，取消其只监听本地服务的设置
4 关闭防火墙关于3306端口的控制，使其接受远程连接sudo ufw allow 3306

----
链接远程服务器:
    ssh soft@120.79.73.175
    密码123

    ssh root@120.79.73.175
    49y9973H


服务器mysql登录
    mysql -usoft -p123 //远程登录帐号,关闭服务器防火墙后，还要在阿里云主页修改 
    mysql -usoft -p123 -h120.79.73.175 -P3306

    mysql -uroot -ptoor //root 用户



