<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="jspf/head.jspf" %>
<body style="padding-top: 0px">
<%@include file="jspf/userTopPanel.jspf" %>
<div class="container">

</div>
<div class="container">

    <!-- Jumbotron Header -->
    <header class="jumbotron my-4">
        <h1 class="display-3"><fmt:message key="index.welcome"/> &lt;mine&gt;SHOP</h1>
        <a href="/getProducts" class="btn btn-primary btn-lg"><fmt:message key="toppanel.products"/> </a>
    </header>

    <c:if test="${!empty sessionScope.gr}">
        <div style="margin-left: 4px;margin-right: 4px" class="row">
            <div class="alert alert-success">
                <a href="#" class="close" data-dismiss="alert">×</a>
                <p>Registration successful</p>
                <% request.getSession().removeAttribute("gr"); %>
            </div>
        </div>
    </c:if>

    <!-- Page Features -->

        <h2><fmt:message key="index.newproducts"/> </h2>
        <div class="row">
            <c:forEach items="${requestScope.products}" var="item">
                <div class="col-md-3">
                    <div class="thumbnail">
                        <img style="height: 252.5px;width: 252.5px"
                             <c:if test="${!empty item.imgInBase64}">src="data:image/png;base64,${item.imgInBase64}"</c:if>
                             <c:if test="${empty item.imgInBase64}">src="images/emptyproduct.png"</c:if>
                             alt="">
                        <div class="caption">
                            <h4 class="pull-right">${item.price} UAH</h4>
                            <h4><a href="product?id=${item.id}">${item.name}</a>
                            </h4>
                            <p>${item.description}</p>
                        </div>

                    </div>
                </div>
            </c:forEach>
        </div>


</div>
<%@include file="jspf/footer.jspf"%>
</body>
</html>