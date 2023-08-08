package com.example.c0423i1module3.controller;

import com.example.c0423i1module3.dao.AuthDAO;
import com.example.c0423i1module3.model.Login;
import com.example.c0423i1module3.service.AuthService;
import com.example.c0423i1module3.util.AppUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

@WebServlet(urlPatterns = "/auths", name = "authController")
public class AuthController  extends HttpServlet {
    private AuthService authService = new AuthService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String page = "auths/login.jsp";
        if(Objects.equals(action, "403")){
            page = "auths/page-403.jsp";
        }
        if(Objects.equals(action, "logout")){
            req.getSession().removeAttribute("login");
        }
        req.getRequestDispatcher(page).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(Objects.equals(action, "login")){
            login(req,resp);
            return;
        }
        if(Objects.equals(action, "register")){
            register(req,resp);
            return;
        }
        req.getRequestDispatcher("auths/login.jsp").forward(req, resp);
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login = (Login) AppUtil.getObjectWithValidation(req, Login.class, new HashMap<>());
        authService.register(login);
        req.setAttribute("message", "Register successfully");
        req.getRequestDispatcher("auths/login.jsp").forward(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            Login login = (Login) AppUtil.getObjectWithValidation(req, Login.class, new HashMap<>());
            authService.login(login, req);
            resp.sendRedirect("/users");
        }catch (RuntimeException exception){
            req.setAttribute("message", exception.getMessage());
            req.getRequestDispatcher("auths/login.jsp").forward(req, resp);
        }
    }

}
