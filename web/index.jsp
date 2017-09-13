<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <h1 class="display-3">Welcome in &lt;epam&gt;SHOP</h1>
        <a href="/getProducts" class="btn btn-primary btn-lg">Go to products</a>
    </header>

    <!-- Page Features -->

        <h2>New products</h2>
        <div class="row">
            <c:forEach items="${requestScope.products}" var="item">
                <div class="col-md-3">
                    <div class="thumbnail">
                        <img src="https://www.techgig.com/files/logo_1490364784_Sony-Xperia-Z3-320x320.jpg" alt="">
                        <div class="caption">
                            <h4 class="pull-right">${item.price}</h4>
                            <h4><a href="product?id=${item.id}">${item.name}</a>
                            </h4>
                            <p>${item.description}</p>
                        </div>
                        <div class="ratings">
                            <p>
                                <span class="glyphicon glyphicon-star"></span>
                                <span class="glyphicon glyphicon-star"></span>
                                <span class="glyphicon glyphicon-star"></span>
                                <span class="glyphicon glyphicon-star"></span>
                                <span class="glyphicon glyphicon-star"></span>
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>


</div>
<%@include file="jspf/footer.jspf"%>
</body>
</html>