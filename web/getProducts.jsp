<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf" %>
<body>
<div class="modal-body row">
    <div class="col-md-3">
        <%@include file="jspf/filter.jspf"%>
    </div>
    <div class="col-md-9">

        <c:forEach begin="0" end="${products.size()/3 + products.size()%3==0?0:1}" var="i">
            <div class="row">
            <c:forEach begin="0" end="2" var="j">
                <c:if test="${i*3+j<=products.size()-1}">
                <div class="col-sm-4 col-lg-4 col-md-4">
                    <div class="thumbnail">
                        <img src="https://www.techgig.com/files/logo_1490364784_Sony-Xperia-Z3-320x320.jpg" alt="">
                        <div class="caption">
                            <h4 class="pull-right">${products[i*3+j].price}</h4>
                            <h4><a href="#">${products[i*3+j].name}</a>
                            </h4>
                            <p>${products[i*3+j].description}<a target="_blank" href="http://www.bootsnipp.com">Bootsnipp - http://bootsnipp.com</a>.</p>
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
                </c:if>
            </c:forEach>
            </div>
        </c:forEach>
        ${countProducts}

    </div>
</div>

</body>
</html>
