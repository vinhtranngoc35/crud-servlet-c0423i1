package com.example.c0423i1module3.service;

import com.example.c0423i1module3.model.Customer;
import com.example.c0423i1module3.model.enums.EGender;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerService {
    private static List<Customer> customerList = new ArrayList<>();

    private static CustomerService customerService;

    static {
        customerList.add(new Customer(1L,2,"tuan","0342", EGender.MALE));
        customerList.add(new Customer(2L,2,"tuan","0342", EGender.MALE));
        customerList.add(new Customer(3L,2,"tuan","0342", EGender.MALE));
        customerList.add(new Customer(4L,2,"tuan","0342", EGender.MALE));
    }

    public List<Customer> findAll(){
        return customerList;
    }

    public void delete(Long id){
        customerList = customerList
                .stream()
                .filter(customer -> !Objects.equals(customer.getId(), id))
                .toList();
    }

    public boolean existById(Long idCustomer){
        var customerOptional = customerList
                .stream()
                .filter(customer -> Objects.equals(customer.getId(), idCustomer))
                .findFirst().orElse(null);
        return customerOptional != null;
    }

    public static CustomerService getInstance(){
        if(customerService == null){
            customerService = new CustomerService();
        }
        return customerService;
    }
    private CustomerService(){}
}
