package com.example.c0423i1module3.controller;

import com.example.c0423i1module3.model.Customer;
import com.example.c0423i1module3.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/customers", name = "customerController")
public class CustomerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("delete".equals(action)){
            delete(req,resp);
            return;
        }
        if("create".equals(action)){
            // App Ultils validation lất cho Id Gender.
        }
        List<Customer> customers = CustomerService.getInstance().findAll();
        // gửi dữ liệu qua trang jsp với parameter 1 là tên biến parameter 2 là value
        req.setAttribute("message", req.getParameter("message"));
        req.setAttribute("customers", customers);
        // response trả về trang list.jsp
        req.getRequestDispatcher("customers/list.jsp")
                .forward(req,resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        Long id = Long.valueOf(req.getParameter("id"));

        if(!CustomerService.getInstance().existById(id)){
            resp.sendRedirect("/customers?message=Id not found");
            return;
        }
        CustomerService.getInstance().delete(id);
        resp.sendRedirect("/customers?message=Deleted");
    }

}
