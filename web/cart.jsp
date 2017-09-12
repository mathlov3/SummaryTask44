<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf" %>
<body style="padding-top: 0px">
<%@include file="jspf/userTopPanel.jspf"%>
    <div class="container">
    <table id="cart" class="table table-hover table-condensed">
        <thead>
        <tr>
            <th style="width:50%">Product</th>
            <th style="width:10%">Price</th>
            <th style="width:8%">Quantity</th>
            <th style="width:22%" class="text-center">Subtotal</th>
            <th style="width:10%"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.cart}" var="item">
        <tr>
            <td data-th="Product">
                <div class="row">
                    <div class="col-sm-2 hidden-xs"><img src="http://placehold.it/100x100" alt="..." class="img-responsive"/></div>
                    <div class="col-sm-10">
                        <h4 class="nomargin">${item.key.name}</h4>
                        <p>${item.key.description}</p>
                    </div>
                </div>
            </td>
            <td data-th="Price">$${item.key.price}</td>
            <form method="post" action="updateCart">
            <td data-th="Quantity">
                <input type="number" name="count" min="1" max="${item.key.count>100?100:item.key.count+1}" class="form-control text-center" value="${item.value}">
            </td>
            <td data-th="Subtotal" class="text-center">$${item.key.price*item.value}</td>
            <td class="actions" data-th="">
                    <input type="hidden" name="id" value="${item.key.id}">
                    <button style="margin-bottom: 5px"  type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-repeat"></span></button>
                </form>
                <form method="post" action="dropFromCart">
                    <input type="hidden" name="id" value="${item.key.id}">
                    <button type="submit" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash"></span></button>
                </form>
            </td>
        </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr class="visible-xs">
            <td class="text-center"><strong>Total 1.99</strong></td>
        </tr>
        <tr>
            <td><a href="#" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
            <td colspan="2" class="hidden-xs"><a href="clearCart" class="btn btn-warning"><i class="fa fa-angle-left"></i>Clear cart</a></td>
            <td class="hidden-xs text-center"><strong>Total $${sessionScope.cart.getPrice()}</strong></td>
            <td><a href="/createOrder" class="btn btn-success btn-block">Create order<i class="fa fa-angle-right"></i></a></td>
        </tr>
        </tfoot>
    </table>
</div>
</body>
</html>
