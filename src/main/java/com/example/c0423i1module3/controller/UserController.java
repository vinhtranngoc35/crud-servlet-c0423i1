package com.example.c0423i1module3.controller;

import com.example.c0423i1module3.model.User;
import com.example.c0423i1module3.service.UserService;
import com.example.c0423i1module3.util.AppConstant;
import com.example.c0423i1module3.util.AppUtil;
import com.example.c0423i1module3.util.RunnableCustom;
import com.example.c0423i1module3.util.RunnableWithRegex;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(urlPatterns = "/users", name = "userController")
public class UserController extends HttpServlet {
    private final String PAGE = "users";

    private Map<String, RunnableCustom> validators;

    private final Map<String, String> errors = new HashMap<>();

    @Override
    public void init() {
        validators = new HashMap<>();
        validators.put("phone", new RunnableWithRegex("[0-9]{10}", "phone", errors, "Phone invalid"));
        //định nghĩa tất cả các fields
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(AppConstant.ACTION);

        if(Objects.equals(action, AppConstant.EDIT)){
            showEdit(req,resp);
            return;
        }
        if (Objects.equals(action, AppConstant.CREATE)) {
            showCreate(req, resp);
            return;
        }
        if (Objects.equals(action, AppConstant.DELETE)) {
            delete(req, resp);
            return;
        }
        showList(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errors.clear();
        String action = req.getParameter(AppConstant.ACTION);
        if (Objects.equals(action, AppConstant.CREATE)) {
            create(req, resp);
            return;
        }
        if (Objects.equals(action, AppConstant.EDIT)) {
            edit(req, resp);
            return;
        }
        showList(req, resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) AppUtil.getObjectWithValidation(req, User.class,  validators);
        if(errors.size() > 0){
            req.setAttribute("user", user);
            req.setAttribute("userJSON", new ObjectMapper().writeValueAsString(user));
            req.setAttribute("message","Something was wrong");
            req.getRequestDispatcher(PAGE + AppConstant.CREATE_PAGE)
                    .forward(req,resp);
        }
        UserService.getUserService().create(user);
        resp.sendRedirect("/users?message=Created");
    }
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) AppUtil.getObject(req, User.class);

        UserService.getUserService().edit(user);
        resp.sendRedirect("/users?message=Edited");
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", UserService.getUserService().getUsers());
        req.setAttribute("message", req.getParameter("message"));
        req.getRequestDispatcher(PAGE + AppConstant.LIST_PAGE).forward(req,resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = new User();

        req.setAttribute("user", user);
        req.setAttribute("userJSON", new ObjectMapper().writeValueAsString(user));
        req.getRequestDispatcher(PAGE + AppConstant.CREATE_PAGE)
                .forward(req,resp);
    }
    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        req.setAttribute("user", UserService.getUserService().findById(id));
        req.setAttribute("userJSON", new ObjectMapper().writeValueAsString(UserService.getUserService().findById(id)));
        req.getRequestDispatcher(PAGE + AppConstant.CREATE_PAGE)
                .forward(req,resp);
    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        if(!UserService.getUserService().existById(id)){
            resp.sendRedirect(PAGE + "?message=Id not found");
            return;
        }
        UserService.getUserService().delete(id);
        resp.sendRedirect(PAGE + "?message=Deleted");
    }
}
