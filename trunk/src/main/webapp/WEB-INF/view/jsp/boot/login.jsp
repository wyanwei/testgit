<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
</head>
<body>
<form action="/boot/login" method="post">
    用户名 : <input type="text" name="username"/>
    密码 : <input type="password" name="password"/>
    <input type="submit" value="登录">
    <span style="color: red"><c:if test="${param.error==true}">用户名或密码输入错误，请您重新输入</c:if></span>
</form>
</body>
</html>
