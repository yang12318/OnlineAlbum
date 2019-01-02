<%@ page language="java" contentType="text/html; charset=GB2312"
         pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%--<script type="text/javascript" src="js/david.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="CSS/STYLE.CSS">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>创建相册</title>
</head>
<body>

<div id="main" class="main">
    <div class="mainAdmin">
        <form onsubmit="return checkFormByName('createAlbumForm');" name="createAlbumForm" action="../createAlbum" method="post">
            <table>
                <tr>
                    <td>相册名字：</td><td style="text-align: left;"><input width="40" type="text" name="name" title="相册名字"></td>
                </tr>
                <tr>
                    <td>相册描述：</td><td><textarea cols="40" rows="5" name="description" title="相册描述"></textarea></td>
                </tr>
                <tr>
                    <td colspan="2" align="right"><input id="submit" onclick="setButtonDisable()" type="submit" value="创建"/>&nbsp;&nbsp;<input id="reset" type="reset" value="清空"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>