<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SqlCmd</title>
</head>
<body>
<c:forEach var="command" items="${commands}">
    <a href=${command}>${command}</a>
    <br>
</c:forEach>
</body>
</html>
