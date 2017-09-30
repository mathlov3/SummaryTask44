<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../jspf/head.jspf" %>
<body style="padding-top: 0px">
<%@include file="../jspf/userTopPanel.jspf" %>
<%@include file="../jspf/menu.jspf" %>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <form method="get" action="getUsers">
        <input type="text" name="login" <c:if test="${requestScope.login!=null}"> value="${requestScope.login}" </c:if> >
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
    <h2><fmt:message key="users.users"/> </h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="orders.userid"/> </th>
            <th><fmt:message key="registration.login"/> </th>
            <th><fmt:message key="registration.name"/> </th>
            <th><fmt:message key="registration.email"/> </th>
            <th><fmt:message key="users.role"/> </th>
            <th>block</th>
            <th>BuySum</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td>${user.key.id}</td>
                <td>${user.key.login}</td>
                <td>${user.key.name}</td>
                <td>${user.key.email}</td>
                <td>
                    <form method="post" action="updateUserStatus">
                        <input type="hidden" name="userId" value="${user.key.id}">
                        <c:if test="${user.key.role == 1}">
                            <input type="hidden" name="newRole" value="2">
                            <button type="submit" class="btn btn-success btn-sm">
                                <span class="glyphicon glyphicon-chevron-down"></span>
                            </button>
                        </c:if>
                        <c:if test="${user.key.role == 2}">
                            <input type="hidden" name="newRole" value="1">
                            <button type="submit" class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-chevron-up"></span>
                            </button>
                        </c:if>
                    </form>
                </td>
                <td>
                    <form method="post" action="updateUserStatus">
                        <input type="hidden" name="userId" value="${user.key.id}">
                        <c:if test="${!user.key.ban}">
                            <input type="hidden" name="banUser" value="1">
                            <button type="submit" class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </c:if>
                        <c:if test="${user.key.ban}">
                            <input type="hidden" name="banUser" value="0">
                            <button type="submit" class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-repeat"></span>
                            </button>
                        </c:if>
                    </form>
                </td>
                <td>
                    ${user.value}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>
</div>
<t:footer/>
</body>
</html>
