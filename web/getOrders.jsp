<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf" %>
<%@include file="jspf/menu.jspf" %>


<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="row">
        <div class="col-md-3">
            <a href="/getOrders?status=1">
                <button type="button" class="btn btn-primary btn-block">New</button>
            </a>
        </div>
        <div class="col-md-3">
            <a href="/getOrders?status=0">
                <button type="button" class="btn btn-default btn-block">All</button>
            </a>
        </div>
        <div class="col-md-3">
            <a href="/getOrders?status=2">
                <button type="button" class="btn btn-success btn-block">Accepted</button>
            </a>
        </div>
        <div class="col-md-3">
            <a href="/getOrders?status=3">
                <button type="button" class="btn btn-danger btn-block">Disabled</button>
            </a>
        </div>
    </div>
    <c:choose>
        <c:when test="${!empty requestScope.fullOrders}">
            <h2 class="sub-header">Orders</h2>
            <div class="table-responsive">
                <c:forEach items="${requestScope.fullOrders}" var="item">
                    <table class="table table-list-search">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>date</th>
                            <th>price</th>
                            <th>status</th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <td>${item.key.id}</td>
                            <td>date</td>
                            <td>${item.key.price}</td>
                            <td>${item.key.orders_status_id}</td>
                        </tr>
                    </table>

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
                            <c:forEach items="${item.value}" var="product">
                            <tbody>
                            <tr>
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>${product.description}</td>
                                <td>${product.count}</td>
                                <td>${product.price}</td>
                                <td>${product.categoryId}</td>
                            </tr>
                            </c:forEach>
                        </table>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <h2>Orders are not exist</h2>
        </c:otherwise>
    </c:choose>
</div>


</div>
</div>
<script src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>


</body>
</html>
