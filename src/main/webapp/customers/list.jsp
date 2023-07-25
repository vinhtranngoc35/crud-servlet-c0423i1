<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/25/2023
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${message}
<table>
    <tr>
        <td>
            Full Name
        </td>
        <td>
            Age
        </td>
        <td>
            Phone
        </td>
        <td>
            Gender
        </td>
        <td>
            Action
        </td>
    </tr>

    <c:forEach items="${customers}" var="customer">
        <tr>
            <td>
                ${customer.fullName}
            </td>
            <td>
                ${customer.age}
            </td>
            <td>
                ${customer.phone}
            </td>
            <td>
                ${customer.eGender}
            </td>
            <td>
                <a onclick="return confirm('Do you to delete by ${customer.fullName}')" href="/customers?action=delete&id=${customer.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
