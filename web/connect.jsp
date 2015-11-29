<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SqlCmd</title>
</head>
<body>
<form action="connect" method="post">
    <table>
        <tr>
            <td>Database name</td>
            <td><input type="text" name="dbname" value="test"></td>
        </tr>
        <tr>
            <td>User name</td>
            <td><input type="text" name="username" value="postgres"></td>
        </tr>
        <tr>
            <td>User password</td>
            <td><input type="password" name="password" value="postgres"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="connect"/></td>
        </tr>
    </table>
</form>
</body>
</html>
