<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Jelly</title>
</head>
<body>
<h1>Edit Jelly</h1>
<form action="jelly" method="post">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="id" value="${jelly.id}">
    <label for="flavor">Flavor:</label>
    <input type="text" name="flavor" id="flavor" value="${jelly.flavor}" required><br>
    <label for="quantity">Quantity:</label>
    <input type="number" name="quantity" id="quantity" value="${jelly.quantity}" required><br>
    <input type="submit" value="Save">
</form>
</body>
</html>