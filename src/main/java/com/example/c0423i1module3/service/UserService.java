package com.example.c0423i1module3.service;

import com.example.c0423i1module3.model.User;
import com.example.c0423i1module3.model.enums.EGender;
import com.example.c0423i1module3.util.AppConstant;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public User findById(Long id){

        return users.stream()
                .filter(user -> Objects.equals(user.getId(), id)) // lọc qua list users với điều kiện là user id == id truyền vào
                .findFirst() // lấy phần tử tìm thấy đầu tiên
                .orElseThrow(() ->  new RuntimeException(String.format(AppConstant.ID_NOT_FOUND, "User")));
            //nếu không tìm thấy thì trả ra lỗi
    }
    public void create(User user){
        user.setId(++currentId);
        users.add(user);
    }

    public static UserService getUserService() {
        return userService;
    }
    private UserService(){}

    public void edit(User user) {
        for (var item : users){
            if(item.getId().equals(user.getId())){
                item.setAvatar(user.getAvatar());
                item.setDob(user.getDob());
                item.setGender(user.getGender());
                item.setPhone(user.getPhone());
                item.setName(user.getName());
                item.setCoverPicture(user.getCoverPicture());
            }
        }
    }

    public boolean existById(Long id) {
        for (var user : users){
            if(Objects.equals(id, user.getId())){
                return true;
            }
        }
        return false;
    }

    public void delete(Long userId) {
        users = users
                .stream()
                .filter(user -> !Objects.equals(user.getId(), userId))
                .collect(Collectors.toList());
    }
}
