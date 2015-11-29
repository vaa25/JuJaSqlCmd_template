<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SqlCommand</title>
</head>
<body>
Существующие команды:<br>
connect|databaseName|userName|password - для подключения к базе данных, с которой будем работать<br>
tables - для получения списка всех таблиц базы, к которой подключились<br>
clear|tableName - для очистки всей таблицы<br>
create|tableName|column1|value1|column2|value2|...|columnN|valueN - для создания записи в таблице<br>
find|tableName - для получения содержимого таблицы 'tableName'<br>
help - для вывода этого списка на экран<br>
exit - для выхода из программы<br>
<br>
<a href="menu">Return to menu</a><br>
</body>
</html>
