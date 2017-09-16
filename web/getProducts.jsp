<!--This page fore simle users that want buy smth-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf" %>
<body style="padding-top: 0px">
<%@include file="jspf/userTopPanel.jspf"%>
<div style="margin-left: 0px;margin-right: 0px" class="modal-body row">
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
                        <img style="height: 252.5px;width: 252.5px" src="data:image/png;base64,${products[i*3+j].imgInBase64}" alt="">
                        <div class="caption">
                            <h4 class="pull-right">${products[i*3+j].price}</h4>
                            <h4><a href="product?id=${products[i*3+j].id}">${products[i*3+j].name}</a>
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
        <ul class="pagination">

            <c:set var="p" value="${requestScope.page}" /> <%-- current page (1-based) --%>
            <c:set var="l" value="5" /> <%-- amount of page links to be displayed --%>
            <c:set var="r" value="${l / 2}" /> <%-- minimum link range ahead/behind --%>
            <c:set var="t" value="${(requestScope.countProducts+1)/9+1}" /> <%-- total amount of pages --%>

            <c:set var="begin" value="${((p - r) > 0 ? ((p - r) < (t - l + 1) ? (p - r) : (t - l)) : 0) + 1}" />
            <c:set var="end" value="${(p + r) < t ? ((p + r) > l ? (p + r) : l) : t}" />


            <c:forEach begin="${begin}" end="${end}" var="ii">
                <li <c:if test="${ii == page}">class="active"</c:if> >
                    <a  href="${pageContext.request.requestURI.replace('.jsp','?')}name=${requestScope.name}${requestScope.categors}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&sort=${requestScope.sort}&page=${ii}">${ii}</a>
                </li>
            </c:forEach>
        </ul>

    </div>
</div>
<%@include file="jspf/footer.jspf"%>
</body>
</html>
