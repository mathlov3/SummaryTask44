<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 09.09.2017
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf"%>
<%@include file="jspf/menu.jspf"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">Products</h2>
            <div class="table-responsive">
                <table class="table table-list-search">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>name</th>
                        <th>description</th>
                        <th>count</th>
                        <th>price</th>
                        <th>category_id</th>
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
                    </tr>
                    </c:forEach>
                </table>
                <ul class="pagination">

                    <c:set var="p" value="${requestScope.page}" /> <%-- current page (1-based) --%>
                    <c:set var="l" value="5" /> <%-- amount of page links to be displayed --%>
                    <c:set var="r" value="${l / 2}" /> <%-- minimum link range ahead/behind --%>
                    <c:set var="t" value="${(sessionScope.countProducts+1)/10+1}" /> <%-- total amount of pages --%>

                    <c:set var="begin" value="${((p - r) > 0 ? ((p - r) < (t - l + 1) ? (p - r) : (t - l)) : 0) + 1}" />
                    <c:set var="end" value="${(p + r) < t ? ((p + r) > l ? (p + r) : l) : t}" />

                    <c:forEach begin="${begin}" end="${end}" var="ii">
                        <li <c:if test="${ii == requestScope.page}">class="active"</c:if> ><a  href="/productList?page=${ii}&category=${requestScope.category}">${ii}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>


</body>
</html>
