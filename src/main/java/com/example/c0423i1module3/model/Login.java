package com.example.c0423i1module3.model;

import com.example.c0423i1module3.model.enums.ERole;

public class Login {
    private Long id;

    private String username;

    private String password;

    private ERole role;

    public Login(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Login() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }
}
