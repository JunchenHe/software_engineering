# 服务端

功能介绍

1. 提供登录功能
2. 成功登录的用户能查看系统管理员发的信息
3. 用户只能查看自己对应等级的信息
4. 登录方式 /login?username=xxxx&password=xxxx
5. 登录是否成功 返回id代表成功 -1代表失败
6. 查看信息的方式 /message?date=xxxx
7. 信息以json的方式返回
8. 包名 cn.edu.zafu.sqh


使用方法

1. 登录

   /login?username=xxxx&password=xxxx

2. 登录状态查看

   /status

3. 信息请求 前提：已登录

   /message?time=xxxxx

4. 退出登录 清空登录信息

   /logout