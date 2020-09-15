<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    boolean hasRole = request.isUserInRole("ROLE_SECURITY_ADMIN");
%>

<table align="left">
    <c:if test="${userInfoVo != null}">
        <tr>
            <th>用户名</th>
            <th>手机号-git主干代码</th>
            <th>邮箱-git分支测试 </th>
            <th>权限</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${userInfoVo}" var="user" varStatus="status">
            <tr>
                <td>${user.userName}</td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
                <td>
                    <c:forEach items="${user.roleVoList}" var="role" varStatus="status">
                        ${role.name}&nbsp;
                    </c:forEach>
                </td>
                <td>
                    <% if(hasRole) {%>
                       <a href="/boot/admin/auth/delete?id=${user.id}">删除</a>
                    <% }%>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5" align="center"><input type="button" value="添加用户信息" onclick="addUserInfo()"/></td>
        </tr>
    </c:if>
</table>
<script type="text/javascript">
    function addUserInfo() {
        window.location.href = "/boot/admin/auth/showadd";
    }
</script>
