package com.iiikn.service;

import com.iiikn.annotaion.Autowired;
import com.iiikn.annotaion.Component;

@Component("userService")
public class UserService implements UserInterface{

    @Autowired
    OderService oderService;
    @Autowired
    Test test;

    @Override
    public String toString() {
        return "UserService{" +
               "oderService=" + oderService +
               '}';
    }


    @Override
    public void test() {
        System.out.println("执行test");
    }
}
