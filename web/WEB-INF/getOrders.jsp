<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
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
                <table class="table table-list-search main" id="myDIV">
                    <thead>
                    <tr >
                        <th class="tr"><fmt:message key="myorders.id"/> </th>
                        <th><fmt:message key="myorders.date"/> </th>
                        <th><fmt:message key="cart.price"/> </th>
                        <th><fmt:message key="orders.userid"/> </th>
                        <th><fmt:message key="myorders.status"/> </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.fullOrders}" var="item">
                        <tr class="tr">
                            <td id="rderid${item.key.id}">${item.key.id}</td>
                            <td>${item.key.date}</td>
                            <td>${item.key.price}</td>
                            <td>${item.key.usersId}</td>
                            <td>${item.key.orders_status_id==1?'new':item.key.orders_status_id==2?'accepted':'disabled'}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <c:forEach items="${requestScope.fullOrders}" var="item">
                <div style="display:none;" id="oorderid${item.key.id}">
                    <button id="orderid${item.key.id}" style="display: none" class="back btn btn-primary">Back</button>

                    <table class="table table-list-search" >
                    <thead>
                    <tr >
                        <th><fmt:message key="myorders.id"/> </th>
                        <th><fmt:message key="getproduct.name"/> </th>
                        <th><fmt:message key="addproduct.description"/> </th>
                        <th><fmt:message key="cart.count"/> </th>
                        <th><fmt:message key="cart.price"/> </th>
                        <th><fmt:message key="productlis.categoryid"/> </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${item.value}" var="product">
                        <tr>
                            <td><a href="product?id=${product.id}">${product.id}</a></td>
                            <td>${product.name}</td>
                            <td>${product.description}</td>
                            <td>${product.count}</td>
                            <td>${product.price}</td>
                            <td>${product.categoryId}</td>
                            <div style="display: none" class="spoiler">Скрытый текст</div>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
                        <c:if test="${item.key.orders_status_id==1}">
                            <form method="post" action="updateOrder" class="pull-right">
                                <input type="hidden" name="id" value="${item.key.id}">
                                <input type="hidden" name="status" value="2">
                                <button type="submit" class="btn btn-success btn-sm"><span
                                        class="glyphicon glyphicon-ok"></span>
                                </button>
                            </form>
                            <form method="post" action="updateOrder">
                                <input type="hidden" name="id" value="${item.key.id}">
                                <input type="hidden" name="status" value="3">
                                <textarea class="form-control" name="content"> </textarea>
                                <button type="submit" class="btn btn-danger btn-sm"><span
                                        class="glyphicon glyphicon-remove"></span>
                                </button>
                            </form>
                        </c:if>
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
<t:footer/>

</body>
</html>
