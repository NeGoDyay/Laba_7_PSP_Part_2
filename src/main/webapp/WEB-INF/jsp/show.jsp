<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jelly Details</title>
</head>
<body>
<h1>Jelly Details</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Flavor</th>
        <th>Quantity</th>
    </tr>
    <tr>
        <td>${jelly.id}</td>
        <td>${jelly.flavor}</td>
        <td>${jelly.quantity}</td>
    </tr>
</table>
<a href="jelly?action=edit&id=${jelly.id}">Edit</a>
<a href="jelly?action=delete&id=${jelly.id}">Delete</a>
</body>
</html>