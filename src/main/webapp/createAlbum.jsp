<%@ page language="java" contentType="text/html; charset=GB2312"
         pageEncoding="GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%--<script type="text/javascript" src="js/david.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="CSS/STYLE.CSS">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>�������</title>
</head>
<body>

<div id="main" class="main">
    <div class="mainAdmin">
        <form onsubmit="return checkFormByName('createAlbumForm');" name="createAlbumForm" action="../createAlbum" method="post">
            <table>
                <tr>
                    <td>������֣�</td><td style="text-align: left;"><input width="40" type="text" name="name" title="�������"></td>
                </tr>
                <tr>
                    <td>���������</td><td><textarea cols="40" rows="5" name="description" title="�������"></textarea></td>
                </tr>
                <tr>
                    <td colspan="2" align="right"><input id="submit" onclick="setButtonDisable()" type="submit" value="����"/>&nbsp;&nbsp;<input id="reset" type="reset" value="���"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>