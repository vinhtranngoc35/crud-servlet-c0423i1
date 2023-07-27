<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/27/2023
  Time: 2:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>User</h1>
<table>
    <thead>
    <tr>
        <th>
            No.
        </th>
        <th>
            Name
        </th>
        <th>
            Phone
        </th>
        <th>
            Email
        </th>
        <th>
            Avatar
        </th>
        <th>
            Date of Birth
        </th>
        <th>
            Gender
        </th>
        <th>
            Cover Picture
        </th>
        <th>
            Action
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user" varStatus="loop">
        <tr>
            <td>
                    ${loop.index + 1}
            </td>
            <td>
                    ${user.name}
            </td>
            <td>
                    ${user.phone}
            </td>
            <td>
                    ${user.email}
            </td>
            <td>
                    ${user.avatar}
            </td>
            <td>
                    ${user.dob}
            </td>
            <td>
                    ${user.gender}
            </td>
            <td>
                    ${user.coverPicture}
            </td>
            <td>
                <button>Edit</button>
                <button>Delete</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
<script>
    function formInput(props, index) {
        if (props.type === 'select') {
            return formSelect(props, index)
        }
        return `<div class="${props.classDiv}">
                <label>${props.label}</label>
                    <input class="input-custom form-control"
                    type="${props.type || 'text'}"
                    name="${props.name}"
                    onblur="onFocus(${index})"
                    ${props.pattern ? 'pattern=${props.pattern}' : ""}
                    value="${props.value}"
                    ${props.require ? 'required' : ''} /></br>
                <span class="error">${props.message}</span>
            </div>`
    }
</script>
</body>
</html>
