<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>Top</title>
</head>
<body>
	<div class="top">	
		<div class="menu">
			<div style="margin: 1px 1px 1px 1px;text-align: left;">
				<c:choose>
					<c:when test="${empty sessionScope.userInfo}">
						<table border="0" style="border-style: none;" width="770"><tr><td align="left">
						<span>您还没有【<a href="login.jsp">登录</a>】，欢迎您【<a href="login.jsp">登录</a>】或者【<a href="addUser.jsp">注册</a>】</span>
						</td></tr></table>
					</c:when>
					<c:otherwise>
					<table border="0" style="border-style: none;" width="770">
					<tr>
						<td align="left">
							欢迎您，${sessionScope.userInfo.nickName}!&nbsp;<span>【<a href="LogoutServlet">注销</a>】</span>
						</td>
						<td align="right">
							<span><a href="GetDefaultInfo?userId=${sessionScope.userInfo.userid}">我的主页</a></span>&nbsp;&nbsp;&nbsp;
							<span><a href="GetUserInfo?userId=${sessionScope.userInfo.userid}">个人信息</a></span>&nbsp;&nbsp;&nbsp;
							<span><a href="GotoAddCategory">添加分类</a></span>&nbsp;&nbsp;&nbsp;
							<span><a href="GotoAddPhoto">添加相片</a></span>&nbsp;&nbsp;&nbsp;
							<span><a href="GetCategories">分类管理</a></span>&nbsp;&nbsp;&nbsp;
							<span><a href="GetPhotos?userId=${sessionScope.userInfo.userid}">相片管理</a></span>&nbsp;&nbsp;&nbsp;
							<span><a href="GetCommentAdminServlet">评论管理</a></span>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
					</table>			
					</c:otherwise>
				</c:choose>
				
			</div>
		</div>
	</div>
</body>
</html>