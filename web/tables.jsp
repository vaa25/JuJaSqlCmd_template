<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SqlCommand</title>
</head>
<body>
Available tables:<br>
<c:forEach var="table" items="${tables}">
    ${table}
    <br>
</c:forEach>
<br>
<a href="menu">Return to menu</a><br>
</body>
</html>
