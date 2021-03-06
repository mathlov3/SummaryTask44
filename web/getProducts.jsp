<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
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
                        <img style="height: 252.5px;width: 252.5px"
                             <c:if test="${!empty requestScope.products[i*3+j].imgInBase64}">src="data:image/png;base64,${requestScope.products[i*3+j].imgInBase64}"</c:if>
                             <c:if test="${empty requestScope.products[i*3+j].imgInBase64}">src="images/emptyproduct.png" </c:if>alt="">
                        <div class="caption">
                            <h4 class="pull-right">${products[i*3+j].price} UAH</h4>
                            <h4><a href="product?id=${products[i*3+j].id}">${products[i*3+j].name}</a>
                            </h4>
                            <h5>${products[i*3+j].description}</h5>

                        </div>
                    </div>
                </div>
                </c:if>
            </c:forEach>
            </div>
        </c:forEach>
        <ul class="pagination">

            <c:set var="p" value="${requestScope.page}" /> <%-- current page (1-based) --%>
            <c:set var="l" value="4" /> <%-- amount of page links to be displayed --%>
            <c:set var="r" value="${l / 2}" /> <%-- minimum link range ahead/behind --%>
            <c:set var="t" value="${(requestScope.countProducts)/6+1}" /> <%-- total amount of pages --%>

            <c:set var="begin" value="${((p - r) > 0 ? ((p - r) < (t - l + 1) ? (p - r) : (t - l)) : 0) + 1}" />
            <c:set var="end" value="${(p + r) < t ? ((p + r) > l ? (p + r) : l) : t}" />


            <c:forEach begin="${begin}" end="${end}" var="ii">
                <li <c:if test="${ii == page}">class="active"</c:if> >
                    <c:if test="${ii!=0}"><a  href="${pageContext.request.requestURI.replace('.jsp','?')}name=${requestScope.name}${requestScope.categors}&minPrice=${requestScope.minPrice}&maxPrice=${requestScope.maxPrice}&sort=${requestScope.sort}&page=${ii}">${ii}</a></c:if>
                </li>
            </c:forEach>
        </ul>

    </div>
</div>
<%@include file="jspf/footer.jspf"%>
</body>
</html>
