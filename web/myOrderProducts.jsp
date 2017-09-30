<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf" %>
<body style="padding-top: 0px">
<%@include file="jspf/userTopPanel.jspf" %>
<div class="container">
    <table id="cart" class="table table-hover table-condensed">
        <thead>
        <tr>
            <th style="width:50%"><b><fmt:message key="cart.products"/> </b></th>
            <th style="width:10%"><b><fmt:message key="cart.price"/> </b></th>
            <th style="width:8%"><b><fmt:message key="cart.count"/> </b></th>
            <th style="width:22%" class="text-center"><b><fmt:message key="cart.all"/> </b></th>
            <th style="width:10%"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.products}" var="item">

            <tr>
                <td data-th="Product">
                    <div class="row">
                        <div class="col-sm-2 hidden-xs"><img
                                <c:if test="${!empty item.imgInBase64}">src="data:image/png;base64,${item.imgInBase64}"</c:if>
                                <c:if test="${empty item.imgInBase64}">src="images/emptyproduct.png"</c:if> alt="..."
                                class="img-responsive"/></div>
                        <div class="col-sm-10">
                            <h4 class="nomargin"><a href="/product?id=${item.id}">${item.name}</a></h4>
                            <p>${item.description}</p>
                        </div>
                    </div>
                </td>
                <td data-th="Price">${item.price} UAH</td>
                <td data-th="Quantity">${item.count}</td>
                <td data-th="Subtotal" class="text-center">${item.price*item.count} UAH</td>
                <td class="actions" data-th="">
                </td>
            </tr>


        </c:forEach>
        </tbody>

    </table>
    <c:if test="${order.orders_status_id==2}">
        <a href="getReport?id=${order.id}">
            <button class="btn btn-primary">Get report</button>
        </a>
    </c:if>
    <c:if test="${order.orders_status_id==3}">
        <b>Cause: </b> ${order.note}
    </c:if>

</div>
<%@include file="jspf/footer.jspf" %>
</body>
</html>
