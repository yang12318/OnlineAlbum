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
						<span>����û�С�<a href="login.jsp">��¼</a>������ӭ����<a href="login.jsp">��¼</a>�����ߡ�<a href="addUser.jsp">ע��</a>��</span>
						</td></tr></table>
					</c:when>
					<c:otherwise>
					<table border="0" style="border-style: none;" width="770">
					<tr>
						<td align="left">
							��ӭ����${sessionScope.userInfo.nickName}!&nbsp;<span>��<a href="LogoutServlet">ע��</a>��</span>
						</td>
						<td align="right">
							<span><a href="GetDefaultInfo?userId=${sessionScope.userInfo.userid}">�ҵ���ҳ</a></span>&nbsp;&nbsp;&nbsp;
							<span><a href="GetUserInfo?userId=${sessionScope.userInfo.userid}">������Ϣ</a></span>&nbsp;&nbsp;&nbsp;
							<span><a href="GotoAddCategory">��ӷ���</a></span>&nbsp;&nbsp;&nbsp;
							<span><a href="GotoAddPhoto">�����Ƭ</a></span>&nbsp;&nbsp;&nbsp;
							<span><a href="GetCategories">�������</a></span>&nbsp;&nbsp;&nbsp;
							<span><a href="GetPhotos?userId=${sessionScope.userInfo.userid}">��Ƭ����</a></span>&nbsp;&nbsp;&nbsp;
							<span><a href="GetCommentAdminServlet">���۹���</a></span>&nbsp;&nbsp;&nbsp;
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