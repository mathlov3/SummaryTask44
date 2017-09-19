<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../jspf/head.jspf" %>
<html>
<body style="padding-top: 0px">
<%@include file="../jspf/userTopPanel.jspf" %>
<%@include file="../jspf/menu.jspf" %>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="row">
        <div class="col-md-3">
            <a href="/getOrders?status=1">
                <button type="button" class="btn btn-primary btn-block"><fmt:message key="orders.new"/> </button>
            </a>
        </div>
        <div class="col-md-3">
            <a href="/getOrders?status=0">
                <button type="button" class="btn btn-default btn-block"><fmt:message key="orders.all"/> </button>
            </a>
        </div>
        <div class="col-md-3">
            <a href="/getOrders?status=2">
                <button type="button" class="btn btn-success btn-block"><fmt:message key="orders.accepted"/> </button>
            </a>
        </div>
        <div class="col-md-3">
            <a href="/getOrders?status=3">
                <button type="button" class="btn btn-danger btn-block"><fmt:message key="orders.disabled"/> </button>
            </a>
        </div>
    </div>
    <c:if test="${!empty sessionScope.status}">
        <div style="margin-left: 4px;margin-right: 4px;margin-top: 10px" class="row">
            <div class="alert alert-info">
                <a href="#" class="close" data-dismiss="alert">×</a>
                <p>${sessionScope.status}</p>
                <% request.getSession().removeAttribute("status"); %>
            </div>
        </div>
    </c:if>
    <c:choose>
        <c:when test="${!empty requestScope.fullOrders}">
            <h2 class="sub-header"><fmt:message key="myorders.orders"/> </h2>
            <div class="table-responsive">
                <table class="table table-list-search">
                    <thead>
                    <tr>
                        <th><fmt:message key="myorders.id"/> </th>
                        <th><fmt:message key="myorders.date"/> </th>
                        <th><fmt:message key="cart.price"/> </th>
                        <th><fmt:message key="orders.userid"/> </th>
                        <th><fmt:message key="myorders.status"/> </th>
                    </tr>
                    </thead>
                </table>

                <c:forEach items="${requestScope.fullOrders}" var="item">

                    <div class="panel panel-default autocollapse">
                        <div class="panel-heading clickable">
                            <h3 class="panel-title">

                                <table style="margin-bottom: 0px" class="table table-list-search">

                                    <tr>
                                        <td>${item.key.id}</td>
                                        <td>date</td>
                                        <td>${item.key.price}</td>
                                        <td>${item.key.usersId}</td>
                                        <td>${item.key.orders_status_id}</td>
                                    </tr>
                                </table>
                            </h3>
                        </div>
                        <div class="panel-body">
                            <table class="table table-list-search">
                                <thead>
                                <tr>
                                    <th><fmt:message key="myorders.id"/> </th>
                                    <th><fmt:message key="getproduct.name"/> </th>
                                    <th><fmt:message key="addproduct.description"/> </th>
                                    <th><fmt:message key="cart.count"/> </th>
                                    <th><fmt:message key="cart.price"/> </th>
                                    <th><fmt:message key="productlis.categoryid"/> </th>
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
                            <c:if test="${item.key.orders_status_id==1}">
                            <form method="post" action="updateOrder" class="pull-right">
                                <input type="hidden" name="id" value="${item.key.id}">
                                <input type="hidden" name="status" value="2">
                                <button type="submit" class="btn btn-success btn-sm"><span
                                        class="glyphicon glyphicon-ok"></span>
                                </button>
                            </form>
                                <div class="pull-right"></div>
                                <form method="post" action="updateOrder" class="pull-right">
                                    <input type="hidden" name="id" value="${item.key.id}">
                                    <input type="hidden" name="status" value="3">
                                    <button type="submit" class="btn btn-danger btn-sm"><span
                                            class="glyphicon glyphicon-remove"></span>
                                    </button>
                                </form>
                                </c:if>
                        </div>
                    </div>
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
<script src="../js/script.js"></script>
<script src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>


</body>
</html>
