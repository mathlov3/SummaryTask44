<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../jspf/head.jspf" %>
<body style="padding-top: 0px">
<%@include file="../jspf/userTopPanel.jspf" %>
<%@include file="../jspf/menu.jspf" %>
<div class="container">

    <form class="form-horizontal" method="post" action="addProduct" enctype="multipart/form-data">
        <fieldset>

            <div class="form-group">
                <label class="col-md-4 control-label" for="product_name"><fmt:message key="getproduct.name"/> </label>
                <div class="col-md-4">
                    <input id="product_name" name="name"  class="form-control input-md"
                           required="" type="text">

                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="product_name_fr"><fmt:message key="addproduct.description"/> </label>
                <div class="col-md-4">
                    <input id="product_name_fr" name="description"
                           class="form-control input-md" required="" type="text">

                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="product_category"><fmt:message key="getproduct.category"/> </label>
                <div class="col-md-4">
                    <select id="product_category" name="category" class="form-control">
                        <c:forEach items="${applicationScope.categories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="available_quantity"><fmt:message key="cart.count"/> </label>
                <div class="col-md-4">
                    <input min="0" id="available_quantity" name="count"
                           class="form-control input-md" required="" type="number">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="available_quantity"><fmt:message key="cart.price"/> </label>
                <div class="col-md-4">
                    <input id="price" name="price"
                           class="form-control input-md" required="" type="text">
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-4 control-label" for="file"><fmt:message key="addproduct.image"/> </label>
                <div class="col-md-4">
                    <input type="file" name="file" id="file" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="alldesc"><fmt:message key="addproduct.description"/> </label>
                <div class="col-md-4">
                    <textarea class="form-control" name="alldesc" id="alldesc"></textarea>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="singlebutton"></label>
                <div class="col-md-4">
                    <button type="submit" id="singlebutton" name="singlebutton" class="btn btn-primary"><fmt:message key="addproduct.addproduct"/>  </button>
                </div>
            </div>

        </fieldset>
    </form>
</div>
</div>
</div>
<t:footer/>
</body>
</html>
