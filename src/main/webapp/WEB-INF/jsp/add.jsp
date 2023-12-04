<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Jelly</title>
</head>
<body>
<h1>Add Jelly</h1>
<form action="jelly" method="post">
    <input type="hidden" name="action" value="add">
    <label for="id">ID:</label>
    <input type="text" name="id" id="id" required><br>
    <label for="flavor">Flavor:</label>
    <input type="text" name="flavor" id="flavor" required><br>
    <label for="quantity">Quantity:</label>
    <input type="number" name="quantity" id="quantity" required><br>
    <input type="submit" value="Add">
</form>
</body>
</html>