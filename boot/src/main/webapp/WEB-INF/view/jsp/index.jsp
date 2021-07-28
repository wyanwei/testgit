<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html lang="en">
<head>
    <script src="<%=basePath%>js/common/jquery-1.8.3.js" type="text/javascript"></script>
</head>
<body>
<h1>hi : ${username}</h1>
<input type="button" onclick="dianjiwo()" value="点我"/>
<input type="button" onclick="logout()" value="退出"/>
</body>
<script type="text/javascript">
    function logout() {
        window.location.href = "/boot/logout";
    }
    function dianjiwo() {
        $.ajax({
            type: 'POST',
            url: "/boot/receive/sj",
            contentType: "application/json;charset=utf-8",
            // 如果想以json格式把数据提交到后台的话，JSON.stringify()必须有，否则只会当做表单提交
            data: JSON.stringify({
                "name": "牙刷",
                "brand": "三笑",
                "price": 12
            }),
            // 期待返回的数据类型
            dataType: "json",
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                alert("error" + data);
            }
        });
    }
</script>

