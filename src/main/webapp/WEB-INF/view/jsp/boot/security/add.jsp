<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form method="post" action="/boot/admin/auth/create" modelAttribute="userAuthorityModel" >
    用户名：<input type="text" value="" name="username"/>${username}<form:errors path="username"/><br>
    密码：<input type="password" value="" name="password"/>${password}<form:errors path="password"/><br>
    手机号：<input type="text" value="" name="phone"/>${phone}<form:errors path="phone"/><br>
    邮箱：<input type="text" value="" name="email"/>${email}<form:errors path="email"/><br>
    权限：<input type="text" value="" name="role"/>${role}<form:errors path="role"/><br>
    权限描述：<input type="text" value="" name="desc"/>${desc}<form:errors path="desc"/><br>
    <input type="submit" value="提交"/>
</form:form>
