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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js" integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js" integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.css" integrity="sha512-3pIirOrwegjM6erE5gPSwkUzO+3cTjpnV9lexlNZqvupR64iZBnOOTiiLPb9M36zpMScbmUNIcHUqKD47M719g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.css" integrity="sha512-3pIirOrwegjM6erE5gPSwkUzO+3cTjpnV9lexlNZqvupR64iZBnOOTiiLPb9M36zpMScbmUNIcHUqKD47M719g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="../style.css" rel="stylesheet" />
</head>
<body>
<div class="container card" style="height: 100vh">
    <div class="toast-body d-none"  id="message">
        ${message}
    </div>


    <!-- Modal -->
    <form method="post" id="form">
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <%--        cần phải có thằng formBody thì xài được validation js--%>
                        <div id="formBody" class="row">

                        </div>
                            <div class="d-flex">
                                <div class="form-check mr-3">
                                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                    <label class="form-check-label" for="flexCheckDefault">
                                        Dale Carnegie
                                    </label>
                                </div>
                                <div class="form-check mr-3">
                                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                    <label class="form-check-label" for="flexCheckDefault">
                                        Uncle Bob
                                    </label>
                                </div>
                                <div class="form-check mr-3">
                                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                    <label class="form-check-label" for="flexCheckDefault">
                                        Example
                                    </label>
                                </div>
                            </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <h1>Book Management</h1>
    <div class="row">
        <div class="col-2">
            <button onclick="onShowPopup()" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                Create
            </button>
            <%--        <a class="btn btn-primary" href="${pageContext.request.contextPath}/users?action=create"> Create</a>--%>
        </div>
        <div class="col-4">

        </div>
        <div class="col-6">
            <form action="${pageContext.request.contextPath}/users" class="row">
                <div class="col-10">
                    <input type="search" name="search" class="form-control">
                </div>

                <div class="col-2">
                    <button class="btn btn-primary">
                        Search
                    </button>
                </div>
            </form>

        </div>
    </div>

    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>
                No.
            </th>
            <th>
                Title
            </th>
            <th>
                Description
            </th>
            <th>
                Publish Date
            </th>
            <th>
                Price
            </th>
            <th>
                Author
            </th>
            <th>
                Category
            </th>
            <th>
                Type
            </th>
            <th>
                Action
            </th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                        1
                </td>
                <td>
                     Clean code
                </td>
                <td>
                    Keep code like simple and sort.
                </td>
                <td>
                     2020-02-02
                </td>
                <td>
                      100.000đ
                </td>
                <td>
                      Uncle Bob, Example
                </td>
                <td>
                      Technical
                </td>
                <td>
                      Single Volume
                </td>
                <td>
                    <button onclick="onShowPopup(1)" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"> Edit</button>
                    <a class="btn btn-danger" >Delete</a>
                </td>
            </tr>
            <tr>
                <td>
                    2
                </td>
                <td>
                    Đắc nhân tâm
                </td>
                <td>
                    Một trong những cuốn sách phát triển bản thân nổi tiếng nhất trên thế giới
                </td>
                <td>
                    2020-02-02
                </td>
                <td>
                    100.000đ
                </td>
                <td>
                    Dale Carnegie, Uncle Bob, Example
                </td>
                <td>
                    Self help
                </td>
                <td>
                    Multiple Volumes
                </td>
                <td>
                    <button onclick="onShowPopup(2)" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"> Edit</button>
                    <a class="btn btn-danger" >Delete</a>
                </td>
            </tr>
        </tbody>

    </table>
</div>


<script src="../base.js" ></script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

<script>
    const books = [{
        id:1,
        title: 'Clean code',
        author: {
            name: 'Uncle Bob',
            id: 1
        },
        description: 'Code short and simple',
        category: {
            id:1
        },
        type: {
            'id': 1
        },
        price: 100000,
        publishDate: '2020-09-09'
    }];
    let user = {};
    let inputs = [];
    const message = document.getElementById('message');
    const btnToast = document.getElementById('liveToastBtn');
    const form = document.getElementById('form');
    const tileModal = document.getElementById("exampleModalLabel");
    window.onload = () => {
        if(message.innerHTML.trim() !== ''){
            toastr.success(message.innerHTML);
        }
    }
    function onShowPopup(id){
        let action = "create";
        let title = "Create";
        if(id){
            action = "edit";
            title = "Edit";
        }
        tileModal.innerHTML = title + " Book";
        form.setAttribute('action', '/users?action='+ action);
        user = books.find(user => user.id === id) || {}; //
        resetData();
    }
    function resetData(){
        inputs = [
            {
                label: "Title",
                name: "name",
                pattern: "^[A-Za-z ]{6,20}",
                message: "Name must have minimum is 6 charters and maximum is 20 charters",
                require: true,
                classDiv: 'col-6',
                value: user.title || ''
            },
            {
                name:'id',
                value: user.id,
                type: 'hidden',
                classDiv: 'd-none'
            },
            {
                label: "Description",
                name: "phone",
                message: "Phone must is number 10 characters",
                require: true,
                value:  user.description || '',
                pattern: "^[0-9]{10}",
                classDiv: 'col-6'
            },
            {
                label: "Price",
                name: "email",
                type: "number",
                message: "Email invalid",
                disable: user.email,
                require: true,
                value:  user.price || '',
                classDiv: 'col-6'
            },
            {
                label: "Publish Date",
                name: "dob",
                type: "date",
                require: true,
                value:  user.publishDate || '',
                classDiv: 'col-6'
            },
            {
                label: "Category",
                name: "role_id",
                type: "select",
                message: "Please choose role",
                options: [{name: 'Technical', value: '1'}],
                require: true,
                value: user.category?.id || '', // có nghĩa là  nếu user.role có giá trị thì sẽ lấy id của role còn không thì lấy ''
                classDiv: 'col-6'
            },
            {
                label: "Type",
                name: "role_id",
                type: "select",
                message: "Please choose role",
                options: [{name: 'Single Volume', value: '1'}],
                require: true,
                value: user.type?.id || '', // có nghĩa là  nếu user.role có giá trị thì sẽ lấy id của role còn không thì lấy ''
                classDiv: 'col-6'
            }
        ];
        const formBody = document.getElementById('formBody'); // DOM formBody theo id
        formBody.innerHTML = '';
        // loop qua inputs
        inputs.forEach((props, index) => {
            // vẽ từng ô input
            formBody.innerHTML += formInput(props, index);
        })
    }
</script>
</body>
</html>
