<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SqlCmd</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        function getColumnNames() {
            $.ajax({
                type: "POST",
                url: document.location.href.substr(0, document.location.href.length - "/create".length) + "/columnnames",
                data: {"tablename": $("#tablename").val()},
                dataType: "json",
                success: function (data) {
                    addColumns(data);
                }
            })
        }
        function addColumns(data) {
            var table = document.getElementById("myTable");
            for (var i = 0; i < data.length; i++) {
                var row = table.insertRow(i + 1);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                cell1.innerHTML = data[i];
                var input = document.createElement('input');
                input.name = data[i];
                input.type = "text";
                cell2.appendChild(input);
            }
            var form = document.getElementById("form");
            final = true;
        }

        var final = false;
        var form = $('#form');
        form.submit(function () {
            return final;
        });

    </script>
</head>
<body>

<form id="form" action="create" method="post">
    <table id="myTable">
        <tr>
            <td>Table name</td>
            <td><input type="text" onchange=getColumnNames(); id="tablename" name="tablename">
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="create"/></td>
        </tr>
    </table>
</form>


</body>

</html>
