<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf" %>
<body>
<div class="container">
    <%@include file="jspf/menu.jspf"%>
    <form class="form-horizontal" method="post" action="addProduct" enctype="multipart/form-data">
        <fieldset>

            <!-- Form Name -->
            <legend>Products</legend>


            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="product_name">Name</label>
                <div class="col-md-4">
                    <input id="product_name" name="name" placeholder="PRODUCT NAME" class="form-control input-md"
                           required="" type="text">

                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="product_name_fr">Description</label>
                <div class="col-md-4">
                    <input id="product_name_fr" name="description" placeholder="PRODUCT DESCRIPTION FR"
                           class="form-control input-md" required="" type="text">

                </div>
            </div>

            <!-- Select Basic -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="product_category">Category</label>
                <div class="col-md-4">
                    <select id="product_category" name="category" class="form-control">
                        <c:forEach items="${applicationScope.categories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="available_quantity">Initial count</label>
                <div class="col-md-4">
                    <input id="available_quantity" name="count" placeholder="AVAILABLE QUANTITY"
                           class="form-control input-md" required="" type="number">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="available_quantity">PRICE</label>
                <div class="col-md-4">
                    <input id="price" name="price" placeholder="AVAILABLE QUANTITY"
                           class="form-control input-md" required="" type="text">
                </div>
            </div>


            <!-- File Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="file">Image</label>
                <div class="col-md-4">
                    <input type="file" name="file" id="file" />
                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="singlebutton"></label>
                <div class="col-md-4">
                    <button type="submit" id="singlebutton" name="singlebutton" class="btn btn-primary">Add product</button>
                </div>
            </div>

        </fieldset>
    </form>
</div>
</div>
</div>
<script src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>
