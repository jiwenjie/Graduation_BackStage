<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>

<form action="/phoneUser/login" method="post">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
    账&nbsp;号：<input type="text" name="username"/>
    <br/><br/>
    密&nbsp;码：<input type="password" name="password"/>
    <%--<br/><br/>--%>
    <%--手机号：<input type="number" name="userphone"/>--%>
    <%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
    <input type="submit" value="登录"/>
</form>


</body>
</html>
