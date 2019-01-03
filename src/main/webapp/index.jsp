<%@ page import="java.util.List" %>
<%@ page import="model.Album" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<link rel="stylesheet" href="CSS/initialization.css">
<link rel="stylesheet" href="CSS/index.css">
<div class="head">
    <nav>
        <ul class="clearfix">
            <li><span><a href="../register">注册</a></span></li>
            <li><span><a href="../login">登录</a></span></li>
        </ul>
    </nav>
    <div class="title">
        <div class="container">
            <div class="title"><h2>欢迎来到poco</h2></div>
            <a class="button" href="../login"><span>登录</span></a>
        </div>
    </div>
</div>
<div class="body">
    <div class="content_container">
        <div class="content_title">
            <div class="icon"></div>
            <h4>相册</h4>
            <div class="icon"></div>
        </div>
    </div>
    <div class="content">
        <%
            List<Album> list = (List<Album>) request.getAttribute("list");
            for(Album album : list) {
                out.println("<div class=\"img clearfix\">");
                out.println("   <div class=\"content_img\">");
                int albumId = album.getId();
                String albumName = album.getName();
                out.print("<a href=\"../album?id=" + albumId + "\">");
                out.print("<img src=\"img/15459680517213526_200478990_S360.jpg\" alt=\"\">");
                out.println("</a>");
                out.println("</div>");
                out.print("<div class=\"imgName\"><span>");
                out.print(albumName);
                out.println("</span></div>");
                out.println("</div>");
            }
        %>
    </div>
</div>
</body>
</html>