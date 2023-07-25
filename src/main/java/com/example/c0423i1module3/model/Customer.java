package com.example.c0423i1module3.model;

import com.example.c0423i1module3.model.enums.EGender;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Long id;

    private Integer age;


    private String fullName;

    private String phone;

    private EGender eGender;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EGender geteGender() {
        return eGender;
    }

    public void seteGender(EGender eGender) {
        this.eGender = eGender;
    }

    //Builder

    public Customer id(Long id){
        this.id = id;
        return this;
    }
    public Customer age(Integer age){
        this.age = age;
        return this;
    }
    public Customer fullName(String fullName){
        this.fullName = fullName;
        return this;
    }
    public Customer phone(String phone){
        this.phone = phone;
        return this;
    }
    public Customer eGender(EGender eGender){
        this.eGender = eGender;
        return this;
    }
}
