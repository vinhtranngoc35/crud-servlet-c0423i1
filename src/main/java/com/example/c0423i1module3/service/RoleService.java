package com.example.c0423i1module3.service;

import com.example.c0423i1module3.dao.RoleDAO;
import com.example.c0423i1module3.model.Role;

import java.util.List;

public class RoleService {

    public static List<Role> getRoles(){
        return new RoleDAO().getRoles();
    }
}
