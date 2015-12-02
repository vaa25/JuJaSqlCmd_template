<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SqlCmd</title>
</head>
<body>
<form action="createRow" method="post">
    <table>
        <c:forEach var="columnname" items="${columnnames}">
            <tr>
                <td>${columnname}</td>
                <td><input type="text" name="${columnname}"></td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td><input type="submit" value="create"/></td>
        </tr>
    </table>
    <input type="hidden" name="tableName" value="${tablename}">
</form>
</body>
</html>
