<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>登录</title>
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
				<form method="post" action="../login">
					<h2>登录</h2>
					<div class="input_container">
						<div class="input" id="name" >
							<span>用户名</span>
							<input type="text" id="Name" name="username">
						</div>
						<div class="input" id="password" >
							<span>密码</span>
							<input type="password" id="Password" name="password"></div>
					</div>
					<input type="submit" value="登录" onclick="verify()">
				</form>
				<a href="signin.html" id="signin">没有账号？点击注册</a>
			</div>
		</div>
	</div>
</div>
</body>
<script src="js/verify.js"></script>
</html>