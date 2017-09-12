<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf"%>
<body style="padding-top: 0px">
<%@include file="jspf/userTopPanel.jspf"%>
<div class="container">

        <div class="thumbnail">
            <img class="img-responsive" src="http://placehold.it/800x300" alt="">
            <div class="caption-full">
                <h4 class="pull-right">${requestScope.product.price}</h4>
                <h4><a href="#">${requestScope.product.name}</a>
                </h4>
                <p>${requestScope.product.description}</p>

            </div>
            <form method="post" action="addToCart">
                <input type="hidden" name="id" value="${product.id}">
                <div class="row">
                    <div class="col-md-1">
                        <input style="width: 60px" name="count" type="number" min="0" max="${product.count>100?100:product.count}" class="form-control text-center" value="1">
                    </div>
                    <div class="col-md-2">
                        <input type="submit" class="btn btn-success" value="Add to cart"</input>
                    </div>
                </div>
            </form>
            <div class="ratings">
                <p class="pull-right">3 reviews</p>
                <p>
                    <span class="glyphicon glyphicon-star"></span>
                    <span class="glyphicon glyphicon-star"></span>
                    <span class="glyphicon glyphicon-star"></span>
                    <span class="glyphicon glyphicon-star"></span>
                    <span class="glyphicon glyphicon-star-empty"></span>
                    4.0 stars
                </p>
            </div>
        </div>



</div>

</body>
</html>
