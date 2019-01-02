<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>注册</title>
</head>
<body>
<link rel="stylesheet" href="css/initialization.css">
<link rel="stylesheet" href="css/Login.css">
<div class="body">
    <div class="body_container">
        <div class="head">
            <nav>
                <ul class="clearfix">
                    <li><span><a href="../register">注册</a></span></li>
                    <li ><span><a href="../login">登录</a></span></li>
                </ul>
            </nav>
        </div>
        <div class="main">
            <div class="main_container">
                <form method="post" action="../register">
                    <h2>注册</h2>
                    <div class="input_container">
                        <div class="input" id="name" >
                            <span>设置用户名</span>
                            <input type="text" id="Name" name="username">
                        </div>
                        <div class="input" id="password" >
                            <span>设置密码</span>
                            <input type="password" id="Password" name="password"></div>
                    </div>
                    <input type="submit" value="注册" onclick="verify()">
                </form>
                <a href="../login" id="signin">已有账号？点击登录</a>
            </div>
        </div>
    </div>
</div>
</body>
<script src="js/verify.js"></script>
</html>