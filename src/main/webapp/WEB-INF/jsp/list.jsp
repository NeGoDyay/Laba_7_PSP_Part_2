<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of Jellies</title>
</head>
<body>
<h1>List of Jellies</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Flavor</th>
        <th>Quantity</th>
        <th>Actions</th>
    </tr>
    <c:forEach items="${jellies}" var="jelly">
        <tr>
            <td>${jelly.id}</td>
            <td>${jelly.flavor}</td>
            <td>${jelly.quantity}</td>
            <td>
                <a href="jelly?action=show&id=${jelly.id}">View</a>
                <a href="jelly?action=edit&id=${jelly.id}">Edit</a>
                <a href="jelly?action=delete&id=${jelly.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="jelly?action=add">Add Jelly</a>
</body>
</html>