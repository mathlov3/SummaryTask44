<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf"%>
<body style="padding-top: 0px">
<%@include file="jspf/userTopPanel.jspf"%>
<div class="container">
<div class="alert alert-warning">
    <strong>Error! </strong>${exception.message}
</div>
</div>

</body>
</html>
