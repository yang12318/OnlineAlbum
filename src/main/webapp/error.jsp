<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript">
        function executeError(){
            var errorForm=document.forms["errorForm"];
            var errorMessage=errorForm.errorMessage.value;
            var returnUrl=errorForm.returnUrl.value;
            //alert(returnUrl);
            alert(errorMessage);
            window.open(returnUrl,"_parent","");
        }
    </script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>错误页</title>
</head>
<body>

<form name="errorForm">
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        String returnUrl = (String) request.getAttribute("returnUrl");
        System.out.println(errorMessage);
        System.out.println(returnUrl);
    %>
    <input type="hidden" value=<%= errorMessage %> name="errorMessage">
    <input type="hidden" value=<%= returnUrl %> name="returnUrl">

</form>
<script type="text/javascript">executeError()</script><a target=""></a>
</body>
</html>