<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 18.09.2017
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf" %>
<body style="padding-top: 0px">
<%@include file="jspf/userTopPanel.jspf" %>
<%@include file="jspf/menu.jspf" %>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="row">
        <form class="form-horizontal" method="post" action="editProduct" enctype="multipart/form-data">
            <fieldset>

                <div class="form-group">
                    <div class="col-md-4">
                        <input value="${product.id}" id="id" name="id" type="hidden">
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="product_name"><fmt:message key="getproduct.name"/> </label>
                    <div class="col-md-4">
                        <input maxlength="45" value="${product.name}" id="product_name" name="name" placeholder="PRODUCT NAME"
                               class="form-control input-md" required="" type="text">
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="product_name_fr"><fmt:message key="addproduct.description"/> </label>
                    <div class="col-md-4">
                        <input maxlength="100" value="${product.description}" id="product_name_fr" name="description"
                               placeholder="PRODUCT DESCRIPTION FR"
                               class="form-control input-md" required="" type="text">

                    </div>
                </div>

                <!-- Select Basic -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="product_category"><fmt:message key="getproduct.category"/> </label>
                    <div class="col-md-4">
                        <select id="product_category" name="category" class="form-control">
                            <c:forEach items="${applicationScope.categories}" var="category">
                                <option value="${category.id}" <c:if
                                    test="${product.categoryId==category.id}">selected="selected"</c:if></option>${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="available_quantity"><fmt:message key="cart.count"/> </label>
                    <div class="col-md-4">
                        <input min="0" max="100" value="${product.count}" id="available_quantity" name="count"
                               placeholder="AVAILABLE QUANTITY"
                               class="form-control input-md" required="" type="number">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="available_quantity"><fmt:message key="cart.price"/> </label>
                    <div class="col-md-4">
                        <input value="${product.price}" id="price" name="price" placeholder="AVAILABLE QUANTITY"
                               class="form-control input-md" required="" type="text">
                    </div>
                </div>


                <!-- File Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="file"><fmt:message key="editproduct.newimage"/> </label>
                    <div class="col-md-4">
                        <input type="file" name="file" id="file"/>
                    </div>
                </div>

                <!-- Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="singlebutton"></label>
                    <div class="col-md-4">
                        <button type="submit" id="singlebutton" name="singlebutton" class="btn btn-primary"><fmt:message key="editproduct.edit"/>
                        </button>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="file"><fmt:message key="addproduct.image"/> </label>
                    <div class="col-md-4">
                        <img class="img-responsive"
                             <c:if test="${!empty requestScope.product.imgInBase64}">src="data:image/png;base64,${requestScope.product.imgInBase64}"</c:if>
                             <c:if test="${empty requestScope.product.imgInBase64}">src="images/emptyproduct.png"</c:if>
                             alt="">
                    </div>
                </div>
            </fieldset>
        </form>
        <form

    </div>

</div>
</div>
</div>

</body>
</html>
