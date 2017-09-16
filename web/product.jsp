<!--This page for simpleusers that want buy product on this page-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf"%>
<body style="padding-top: 0px">
<%@include file="jspf/userTopPanel.jspf"%>
<c:if test="${!empty sessionScope.okb}">
    <div style="margin-left: 4px;margin-right: 4px" class="row">
        <div class="alert alert-success">
            <a href="#" class="close" data-dismiss="alert">×</a>
            <p>Product added to cart</p>
            <% request.getSession().removeAttribute("okb"); %>
        </div>
    </div>
</c:if>
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
                        <input type="submit" class="btn btn-success" value="Add to cart"></input>
                    </div>
                </div>
            </form>
            <div class="ratings">
                <p class="pull-right">3 reviews</p>
                <p>
                <div class="star-rating">
                    <span class="fa fa-star-o" data-rating="1"></span>
                    <span class="fa fa-star-o" data-rating="2"></span>
                    <span class="fa fa-star-o" data-rating="3"></span>
                    <span class="fa fa-star-o" data-rating="4"></span>
                    <span class="fa fa-star-o" data-rating="5"></span>
                    <input type="hidden" name="whatever" class="rating-value" value="3">
                </div>
                </p>
            </div>
        </div>
    <form method="post" action="/addCommentary">
        <input type="hidden" name="id" value="${product.id}"/>
        <textarea name = "content" class="form-control" rows="3" placeholder="Your commentary" required></textarea>
        <button type="submit" class="btn btn-primary">Add comment</button>
    </form>

    <c:forEach items="${requestScope.commentaries}" var="comment">
    <div class="row">
        <div class="col-sm-1">
            <div class="thumbnail">
                <img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
            </div><!-- /thumbnail -->
        </div><!-- /col-sm-1 -->

        <div class="col-sm-11">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong>${comment.value==null?'unknown':comment.value.login}</strong> <span class="text-muted">${comment.key.date}</span>
                </div>
                <div class="panel-body">
                    ${comment.key.content}
                </div><!-- /panel-body -->
            </div><!-- /panel panel-default -->
        </div><!-- /col-sm-5 -->
    </div>
    </c:forEach>



</div>
<%@include file="jspf/footer.jspf"%>
</body>
</html>
