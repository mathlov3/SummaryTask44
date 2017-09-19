<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf"%>
<body style="padding-top: 0px">
<%@include file="jspf/userTopPanel.jspf"%>
<div class="container">
    <h2><fmt:message key="myorders.orders"/> </h2>
    <table class="table table-striped">
        <tr>
            <td><b><fmt:message key="myorders.id"/></b></td>
            <td><b><fmt:message key="myorders.status"/></b></td>
            <td><b><fmt:message key="myorders.date"/></b></td>
            <td><b><fmt:message key="myorders.price"/></b></td>
        </tr>
        <c:forEach items="${requestScope.orders}" var="order">
            <tr>
                <td><a href="orderProducts?orderId=${order.id}">#${order.id}</a></td>
                <td>${order.orders_status_id}</td>
                <td>date</td>
                <td>${order.price}</td>

            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="jspf/footer.jspf"%>
</body>
</html>
