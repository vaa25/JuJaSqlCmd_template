<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SqlCmd</title>
</head>
<body>
<form action="find" method="post">
    <table>
        <tr>
            <td>Table name</td>
            <td><input type="text" name="tablename" value="test"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="find"/></td>
        </tr>
    </table>
</form>
</body>
</html>
