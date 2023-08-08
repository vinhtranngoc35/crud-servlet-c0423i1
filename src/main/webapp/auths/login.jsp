<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 8/8/2023
  Time: 3:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
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
<form method="post" id="form" action="/auths?action=register">
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">

          <%--        cần phải có thằng formBody thì xài được validation js--%>
          <div id="formBodyOfRegister" class="row">

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
<div class="container-fluid">
  <div class="row" style="height: 100vh; display: flex; align-items: center">
    <div class="col-4"></div>
    <div class="col-4 card" style="display: flex; align-items: center">
      <form method="post" action="${pageContext.request.contextPath}/auths?action=login">
        <div id="formBody">

        </div>
        <button class="btn btn-primary" type="submit">Login</button>
        <button onclick="onShowPopup()" class="btn btn-primary"  data-bs-toggle="modal" data-bs-target="#exampleModal">Register</button>
      </form>
    </div>
    <div class="col-4"></div>
  </div>
  <div class="toast-body d-none"  id="message">
    ${message}
  </div>

</div>


<script src="../base.js" ></script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script>
  const formBody = document.getElementById('formBody');
  const formBodyRegister = document.getElementById('formBodyOfRegister');
  const message = document.getElementById('message');

  window.onload = () => {
    if(message.innerHTML.trim() !== ''){
      toastr.success(message.innerHTML);
    }
  }
  let inputs = [
    {
      label: "Username",
      name: "username",
      message: "Username must is number 10 characters",
      require: true,
      value:   '',
      pattern: "^[a-zA-Z0-9]{6,25}",
      classDiv: 'col-12'
    },
    {
      label: "Password",
      name: "password",
      type: "password",
      message: "Password invalid",
      pattern: "^[a-zA-Z0-9]{6,25}",
      require: true,
      value:  '',
      classDiv: 'col-12'
    },
  ];
  let inputsRegister = [
    {
      label: "Username",
      name: "username",
      message: "Username must is number 10 characters",
      require: true,
      value:   '',
      pattern: "^[a-zA-Z0-9]{6,25}",
      classDiv: 'col-12'
    },
    {
      label: "Password",
      name: "password",
      type: "password",
      message: "Password invalid",
      pattern: "^[a-zA-Z0-9]{6,25}",
      require: true,
      value:  '',
      classDiv: 'col-12'
    },
    {
      label: "Confirm Password",
      name: "confirm-password",
      type: "password",
      message: "Password invalid",
      pattern: "^[a-zA-Z0-9]{6,25}",
      require: true,
      value:  '',
      classDiv: 'col-12'
    },
  ];
  inputsRegister.forEach((input, index) => {
    formBodyRegister.innerHTML += formInput(input, index);
  })
  inputs.forEach((input, index) => {
    formBody.innerHTML += formInput(input, index);
  })
</script>
</body>
</html>
