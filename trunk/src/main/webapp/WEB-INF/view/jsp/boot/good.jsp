<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form method="post" action="/boot/good/show">
    检索：<input type="text" value="${name}" name="name"/>
    <input type="submit" value="检索"/>
</form>
<table>
    <c:if test="${goodsList != null}">
        <tr>
            <th>商品名称</th>
            <th>数量</th>
            <th>创建时间</th>
        </tr>
        <c:forEach items="${goodsList}" var="good" varStatus="status">
            <tr>
                <td>${good.name}</td>
                <td>${good.number}</td>
                <td><fmt:formatDate value="${good.updateTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
<form method="post" action="/boot/good/add">
    商品名称：<input type="text" value="" name="name"/>
    数量：<input type="text" value="" name="number"/>
    <input type="submit" value="提交"/>
</form>
