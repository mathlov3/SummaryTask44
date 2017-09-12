<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 12.09.2017
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf" %>
<body style="padding-top: 0px">
<%@include file="jspf/userTopPanel.jspf" %>
<div class="container">
    <table id="cart" class="table table-hover table-condensed">
        <thead>
        <tr>
            <th style="width:50%"><b>Product</b></th>
            <th style="width:10%"><b>Price</b></th>
            <th style="width:8%"><b>Quantity</b></th>
            <th style="width:22%" class="text-center"><b>Subtotal</b></th>
            <th style="width:10%"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.products}" var="item">

            <tr>
                <td data-th="Product">
                    <div class="row">
                        <div class="col-sm-2 hidden-xs"><img src="http://placehold.it/100x100" alt="..."
                                                             class="img-responsive"/></div>
                        <div class="col-sm-10">
                            <h4 class="nomargin"><a href="/product?id=${item.id}">${item.name}</a></h4>
                            <p>${item.description}</p>
                        </div>
                    </div>
                </td>
                <td data-th="Price">$${item.price}</td>
                <td data-th="Quantity">${item.count}</td>
                <td data-th="Subtotal" class="text-center">$${item.price*item.count}</td>
                <td class="actions" data-th="">
                </td>
            </tr>

        </c:forEach>
        </tbody>

    </table>
</div>

</body>
</html>
