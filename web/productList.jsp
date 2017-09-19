<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="jspf/head.jspf" %>
<html>
<body style="padding-top: 0px">
<%@include file="jspf/userTopPanel.jspf" %>
<%@include file="jspf/menu.jspf" %>


<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <c:choose>
        <c:when test="${!empty requestScope.products}">
            <h2 class="sub-header"><fmt:message key="cart.products"/> </h2>
            <div class="table-responsive">
                <table class="table table-list-search">
                    <thead>
                    <tr>
                        <th><fmt:message key="myorders.id"/> </th>
                        <th><fmt:message key="getproduct.name"/> </th>
                        <th><fmt:message key="addproduct.description"/> </th>
                        <th><fmt:message key="cart.count"/> </th>
                        <th><fmt:message key="cart.count"/> </th>
                        <th><fmt:message key="productlis.categoryid"/> </th>
                        <th>edit</th>
                    </tr>
                    </thead>
                    <c:forEach items="${requestScope.products}" var="item">
                    <tbody>
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>${item.description}</td>
                        <td>${item.count}</td>
                        <td>${item.price}</td>
                        <td>${item.categoryId}</td>
                        <td><form method="get" action="editProduct">
                                <input type="hidden" name="id" value="${item.id}">
                                <button type="submit" class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </button>
                        </form></td>
                    </tr>
                    </c:forEach>
                </table>
                <ul class="pagination">

                    <c:set var="p" value="${requestScope.page}"/> <%-- current page (1-based) --%>
                    <c:set var="l" value="5"/> <%-- amount of page links to be displayed --%>
                    <c:set var="r" value="${l / 2}"/> <%-- minimum link range ahead/behind --%>
                    <c:set var="t" value="${(sessionScope.countProducts+1)/10+1}"/> <%-- total amount of pages --%>

                    <c:set var="begin" value="${((p - r) > 0 ? ((p - r) < (t - l + 1) ? (p - r) : (t - l)) : 0) + 1}"/>
                    <c:set var="end" value="${(p + r) < t ? ((p + r) > l ? (p + r) : l) : t}"/>

                    <c:forEach begin="${begin}" end="${end}" var="ii">
                        <li
                                <c:if test="${ii == requestScope.page}">class="active"</c:if> ><a
                                href="/productList?page=${ii}&category=${requestScope.category}">${ii}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </c:when>
        <c:otherwise>
            <h2>Products are not exist</h2>
        </c:otherwise>
    </c:choose>
</div>


</div>
</div>
<script src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>


</body>
</html>
