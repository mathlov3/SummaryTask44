<%--
  Created by IntelliJ IDEA.
  User: Eugene
  Date: 17.09.2017
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf" %>
<body style="padding-top: 0px">
<%@include file="jspf/userTopPanel.jspf" %>

<div class="container main-content">
    <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-4"></div>
        <div class="col-lg-4 col-md-4 col-sm-4">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <header>
                            <h1 class="center-text">Registration</h1>
                        </header>
                        <form method="post" action="registration" id="registration">
                            <c:if test="${!empty errors.get('name') }">
                                <div class="alert alert-danger">
                                    <a href="#" class="close" data-dismiss="alert">×</a>
                                        ${errors.get('name') }
                                </div>
                            </c:if>
                            <div class="form-group ">
                                <label class="control-label" for="name">Name</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="Имя" value="${user.name }" />
                            </div>
                            <c:if test="${!empty errors.get('login') }">
                                <div class="alert alert-danger">
                                    <a href="#" class="close" data-dismiss="alert">×</a>
                                        ${errors.get('login') }
                                </div>
                            </c:if>
                            <div class="form-group ">
                                <label class="control-label requiredField" for="login">Login
                                    <span class="asteriskField"> * </span></label>
                                <input type="text" class="form-control" id="login" name="login" placeholder="login"  value="${user.login }" />
                            </div>
                            <div class="form-group ">
                                <label class="control-label requiredField" for="login">Email
                                    <span class="asteriskField"> * </span></label>
                                <input type="text" class="form-control" id="email" name="email" placeholder="email"  value="${user.login }" />
                            </div>
                            <c:if test="${!empty errors.get('password') }">
                                <div class="alert alert-danger">
                                    <a href="#" class="close" data-dismiss="alert">×</a>
                                        ${errors.get('password') }
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label class="control-label requiredField" for="password">Пароль
                                    <span class="asteriskField"> * </span>
                                </label> <input type="password" class="form-control" id="password" name="password"
                                                placeholder="пароль" />
                            </div>
                            <c:if test="${!empty errors.get('password2') }">
                                <div class="alert alert-danger">
                                    <a href="#" class="close" data-dismiss="alert">×</a>
                                        ${errors.get('password2') }
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label class="control-label requiredField" for="repassword">Подтверждение пароля
                                    <span class="asteriskField"> * </span>
                                </label> <input type="password" class="form-control" id="repassword" name="repassword"
                                                placeholder="подтверждение" />
                            </div>
                            <div class="form-group">
                                <div>
                                    <button class="btn btn-primary " name="submit" type="submit">Зарегистрироваться</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-4"></div>
    </div>
</div>
<!-- /#contentContainer -->
<script src="js/myvalidator.js"></script>

</body>
</html>
