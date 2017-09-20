<!--This page for simpleusers that want buy product on this page-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf" %>
<body style="padding-top: 0px">
<%@include file="jspf/userTopPanel.jspf" %>
<c:if test="${!empty sessionScope.okb}">
    <div style="margin-left: 4px;margin-right: 4px" class="row">
        <div class="alert alert-success">
            <a href="#" class="close" data-dismiss="alert">×</a>
            <p>Product added to cart</p>
            <% request.getSession().removeAttribute("okb"); %>
        </div>
    </div>
</c:if>
<c:if test="${!empty sessionScope.voteStatus}">
    <div style="margin-left: 4px;margin-right: 4px" class="row">
        <div class="alert alert-info">
            <a href="#" class="close" data-dismiss="alert">×</a>
            <p>${sessionScope.voteStatus}</p>
            <% request.getSession().removeAttribute("voteStatus"); %>
        </div>
    </div>
</c:if>
<div class="container">

    <div class="thumbnail">
        <div class="row">
            <div class="col-md-6" align="center">
                <img style="max-height: 320px" class="img-responsive"
                     <c:if test="${!empty requestScope.product.imgInBase64}">src="data:image/png;base64,${requestScope.product.imgInBase64}"</c:if>
                     <c:if test="${empty requestScope.product.imgInBase64}">src="images/emptyproduct.png"</c:if>
                     alt="">
            </div>
            <div class="col-md-6">
                <h4><a href="#">${requestScope.product.name}</a>
                </h4>
                <p>${requestScope.product.description}</p>
            </div>

        </div>

        <div class="caption-full">
            <h4 class="pull-right"><fmt:message key="getproduct.price"/> - ${requestScope.product.price}</h4>
        </div>
        <br>
        <form method="post" action="addToCart">
            <c:if test="${product.count!=0}">
                <input type="hidden" name="id" value="${product.id}">
                <div class="row">
                    <div class="col-md-1">
                        <input style="width: 60px" name="count" type="number" min="0"
                               max="${product.count>100?100:product.count}" class="form-control text-center" value="1">
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-success"><fmt:message key="product.addtocart"/></button>
                    </div>
                </div>
            </c:if>
            <c:if test="${product.count==0}">
                <div class="row">
                    <div class="col-md-12">
                        <p><b><fmt:message key="product.empty"/></b></p>
                    </div>
                </div>
            </c:if>
        </form>
        <div class="ratings">
            <p>
                <a href="addVote?id=${product.id}&vote=1"><span
                        <c:if test="${requestScope.vote>=1}">style="color: yellow"</c:if> class="fa fa-star-o"
                        data-rating="1"></span></a>
                <a href="addVote?id=${product.id}&vote=2"><span
                        <c:if test="${requestScope.vote>=2}">style="color: yellow"</c:if> class="fa fa-star-o"
                        data-rating="2"></span></a>
                <a href="addVote?id=${product.id}&vote=3"><span
                        <c:if test="${requestScope.vote>=3}">style="color: yellow"</c:if> class="fa fa-star-o"
                        data-rating="3"></span></a>
                <a href="addVote?id=${product.id}&vote=4"><span
                        <c:if test="${requestScope.vote>=4}">style="color: yellow"</c:if> class="fa fa-star-o"
                        data-rating="4"></span></a>
                <a href="addVote?id=${product.id}&vote=5"><span
                        <c:if test="${requestScope.vote>=5}">style="color: yellow"</c:if> class="fa fa-star-o"
                        data-rating="5"></span></a>
            </p>
        </div>
    </div>
    <c:if test="${requestScope.images.size() !=  0}">
        <div class="thumbnail">
            <h2 class="sub-header"><fmt:message key="editproduct.images"/> </h2>
            <c:forEach begin="0" end="${requestScope.images.size() %2==0?requestScope.images.size()/2:requestScope.size/2+1}"
                       var="i">
                <div class="row" align="center">

                    <div class="col-md-6"><img class="img-responsive" style="max-height: 320px"
                                               src="data:image/png;base64,${images[i*2].base64Img}" alt=""></div>
                    <c:if test="${requestScope.images.size()!=i*2}">
                        <div class="col-md-6"><img class="img-responsive" style="max-height: 320px"
                                                   src="data:image/png;base64,${images[i*2+1].base64Img}" alt=""></div>
                    </c:if>
                </div>
                <br>
            </c:forEach>
        </div>
    </c:if>
    <form method="post" action="/addCommentary">
        <input type="hidden" name="id" value="${product.id}"/>
        <textarea name="content" class="form-control" rows="3" placeholder=
        <fmt:message key="product.youcommentary"/> required></textarea>
        <br>
        <button type="submit" class="btn btn-primary"><fmt:message key="product.addcomment"/></button>
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
                        <strong>${comment.value==null?'unknown':comment.value.login}</strong> <span
                            class="text-muted">${comment.key.date}</span>
                    </div>
                    <div class="panel-body">
                            ${comment.key.content}
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>


</div>
<%@include file="jspf/footer.jspf" %>
</body>
</html>
