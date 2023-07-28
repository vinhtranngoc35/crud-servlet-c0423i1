<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/27/2023
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Create</h1>
    <form method="post" action="/users?action=create">
        <div>
            <label>nhap ten</label>
            <input name="name" type="text" value="${user.name}">
        </div>
        <div>
            <label>nhap so dien thoai</label>
            <input name="phone" type="text" value="${user.phone}">
        </div>
        <div>
            <label>nhap email</label>
            <input name="email" type="email" value="${user.email}">
        </div>
        <div>
            <label>nhap avatar</label>
            <input name="avatar" type="text" value="${user.avatar}">
        </div>
        <div>
            <select name="gender">
                <option value="MALE">MALE</option>
                <option value="FEMALE">FEMALE</option>
            </select>
        </div>
        <div>
            <label>nhap ngay sinh</label>
            <input name="dob" type="date" value="${user.dob}">
        </div>
        <div>
            <label>nhap coverpicture</label>
            <input name="coverPicture" type="text" value="${user.coverPicture}">
        </div>
        <button type="submit">Tao user</button>
        <button type="button">quay lai</button>
    </form>
</body>
</html>
