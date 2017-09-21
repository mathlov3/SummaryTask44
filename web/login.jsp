<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="jspf/head.jspf" %>
<body style="padding-top: 0px">

<%@include file="jspf/userTopPanel.jspf"%>
<div class="container">
    <div class="row vertical-offset-100">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please sign in</h3>
                </div>
                <div class="panel-body">
                    <form method="post" action="login">
                        <fieldset>
                            <c:if test="${!empty sessionScope.err}">
                            <div style="margin-left: 4px;margin-right: 4px" class="row">
                                <div class="alert alert-danger">
                                    <a href="#" class="close" data-dismiss="alert">×</a>
                                        <p>${sessionScope.err }</p>
                                    <% request.getSession().removeAttribute("err"); %>
                                </div>
                            </div>
                            </c:if>
                            <div class="form-group">
                                <input class="form-control" type="text" placeholder="Enter Username" name="login" value="${sessionScope.login}"
                                       required>
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="password" placeholder="Enter Password" name="password"
                                       required>
                            </div>
                            <button class="btn btn-lg btn-success btn-block" type="submit">Sign in</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="jspf/footer.jspf"%>
</body>
</html>
