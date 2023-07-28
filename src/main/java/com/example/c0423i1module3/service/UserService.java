package com.example.c0423i1module3.service;

import com.example.c0423i1module3.model.User;
import com.example.c0423i1module3.model.enums.EGender;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static List<User> users;

    private static Long currentId = 0L;

    private static final UserService userService;

    static {
        users = new ArrayList<>();
        users.add(new User(++currentId, "Vinh", "Vinh2","Vinh3","Vinh4", EGender.MALE,  Date.valueOf("1994-07-29"), "Demo"));
        users.add(new User(++currentId, "Vinh", "Vinh2","Vinh3","Vinh4", EGender.MALE,  Date.valueOf("1994-07-29"), "Demo"));
        users.add(new User(++currentId, "Vinh", "Vinh2","Vinh3","Vinh4", EGender.MALE,  Date.valueOf("1994-07-29"), "Demo"));
        users.add(new User(++currentId, "Vinh", "Vinh2","Vinh3","Vinh4", EGender.MALE,  Date.valueOf("1994-07-29"), "Demo"));
        userService = new UserService();
    }

    public List<User> getUsers(){
        return users;
    }
    public void create(User user){
        user.setId(++currentId);
        users.add(user);
    }

    public static UserService getUserService() {
        return userService;
    }
    private UserService(){}
}
