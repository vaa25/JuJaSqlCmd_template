<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SqlCmd</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        function addParameters() {
            $.ajax({
                type: "POST",
                url: "/columnnames",
                data: "tablename=" + $("#tablename").val(),
                success: function (data) {
                    console.log(data);
                    var table = document.getElementById("myTable");
                    for (var i = 0; i < data.length; i++) {
                        var row = table.insertRow(i + 1);
                        var cell1 = row.insertCell(0);
                        var cell2 = row.insertCell(1);
                        cell1.innerHTML = data[i];
                        var input = document.createElement('input');
                        input.type = "file";
                        input.name = data[i];
                        input.type = "text";
                        cell2.appendChild(input);
                    }
                }
            })
        }
        $(function () {
            $('.buttom').click(function () {
                $('#form1').submit();
            });

        });
    </script>
</head>
<body>

<form id="form" onsubmit="return false;">
    <table id="myTable">
        <tr>
            <td>Table name</td>
            <td><input type="text" onkeyup="" onchange=addParameters(); id="tablename" name="tablename" value="role">
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="create" class="buttom"/></td>
        </tr>
    </table>
</form>


</body>

</html>
