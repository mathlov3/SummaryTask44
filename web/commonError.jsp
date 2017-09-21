<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="jspf/head.jspf"%>
<body>
<%@include file="jspf/userTopPanel.jspf"%>
<div class="container">
    <div class="jumbotron">
        <div class="text-center"><i class="fa fa-5x fa-frown-o" style="color:#d9534f;"></i></div>
        <h1 class="text-center">Inner error<p> </p><p><small class="text-center"> Oh noes everything broke</small></p></h1>
        <p class="text-center">Try pressing the back button or clicking on this button.</p>
        <p class="text-center"><a class="btn btn-primary" href="index"><i class="fa fa-home"></i> Take Me Home</a></p>
    </div>
</div>
<%@include file="jspf/footer.jspf"%>
</body>
</html>
