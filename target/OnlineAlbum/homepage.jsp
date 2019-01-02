<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="CSS/STYLE.CSS">
    <!--<script type="text/javascript" src="js/david.js"></script> -->

    <meta http-equiv="Content-Type" content="text/html; charset=GB2312">
    <title>主页</title>
</head>
<body>

<div id="main" class="main">
    <div class="left">
        <div class="userInfo">
            <div class="imageDiv">
                <img class="userImage" src="IMAGES/USER.JPG"/>
            </div>
            <span><a style="text-decoration: none;" href="homepage?userId=${requestScope.userInfo.userid }">${requestScope.userInfo.nickName }</a></span>
        </div>
    </div>
    <div class="photoes">
        <c:choose>
            <c:when test="${empty requestScope.categoryList}">
                该用户还没有相册！
            </c:when>
            <c:otherwise>
                <c:forEach items="${requestScope.categoryList}" var="category">
                    <div class="category">
                        <div style="width: 115px;height: 125px; border-width: 0px;border-style: solid;border-color: #666633;"><a href="GetPhotosByCate?categoryId=${category.categoryid }"><img class="categoryPhoto" src="images\\category.JPG"/></a></div>
                        <hr>
                        <div style="width: 115px;height: 20px; border-width: 0px;border-style: solid;border-color: #666633;font-size: 14px;color: #666633;"><a href="GetPhotosByCate?categoryId=${category.categoryid }">${category.name }(2)</a></div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>