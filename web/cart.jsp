<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf" %>
<body style="padding-top: 0px;min-height: 100%">
<%@include file="jspf/userTopPanel.jspf" %>
<div class="container">
    <table id="cart" class="table table-hover table-condensed">
        <thead>
        <tr>
            <th style="width:50%"><fmt:message key="cart.products"/> </th>
            <th style="width:10%"><fmt:message key="cart.price"/> </th>
            <th style="width:8%"><fmt:message key="cart.count"/> </th>
            <th style="width:22%" class="text-center"><fmt:message key="cart.all"/> </th>
            <th style="width:10%"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.cart}" var="item">
            <tr>
                <td data-th="Product">
                    <div class="row">
                        <div class="col-sm-2 hidden-xs"><img
                                <c:if test="${!empty item.key.imgInBase64}">src="data:image/png;base64,${item.key.imgInBase64}"</c:if>
                                <c:if test="${empty item.key.imgInBase64}">src="images/emptyproduct.png"</c:if>
                                class="img-responsive"/></div>
                        <div class="col-sm-10">
                            <h4 class="nomargin">${item.key.name}</h4>
                            <p>${item.key.description}
                                <c:forEach items="${requestScope.errors}" var="error">
                                <c:if test="${error.id eq item.key.id}">
                            <p style="color: red">(On storage - ${error.count})</p>
                            </c:if>
                            </c:forEach> </p>
                        </div>
                    </div>
                </td>
                <td data-th="Price">$${item.key.price}</td>
                <form method="post" action="updateCart">
                    <td data-th="Quantity">
                        <input type="number" name="count" min="1" max="${item.key.count>100?100:item.key.count}"
                               class="form-control text-center" value="${item.value}">
                    </td>
                    <td data-th="Subtotal" class="text-center">$${item.key.price*item.value}</td>
                    <td class="actions" data-th="">
                        <input type="hidden" name="id" value="${item.key.id}">
                        <button style="margin-bottom: 5px" type="submit" class="btn btn-primary btn-sm"><span
                                class="glyphicon glyphicon-repeat"></span></button>
                </form>
                <form method="post" action="dropFromCart">
                    <input type="hidden" name="id" value="${item.key.id}">
                    <button type="submit" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash"></span>
                    </button>
                </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td><a href="#" class="btn btn-warning"><i class="fa fa-angle-left"></i><fmt:message key="cart.continue"/> </a></td>
            <td colspan="2" class="hidden-xs"><a href="clearCart" class="btn btn-warning"><i
                    class="fa fa-angle-left"></i><fmt:message key="cart.clearcart"/> </a></td>
            <td class="hidden-xs text-center"><strong><fmt:message key="cart.allall"/> ${sessionScope.cart.getPrice()}</strong></td>
            <td><a href="/createOrder" class="btn btn-success btn-block"><fmt:message key="cart.create"/> <i
                    class="fa fa-angle-right"></i></a></td>
        </tr>
        </tfoot>
    </table>

</div>

<%@include file="jspf/footer.jspf" %>
</body>
</html>
