package com.iiikn.service;


import com.iiikn.SudokuApplication;
import com.iiikn.core.ApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = SudokuApplication.run(AppConfig.class);

        UserInterface userService = (UserInterface) context.getElement("userService");
        userService.test();

        System.out.println(((UserService) userService).test);
        context.close();

        userService = (UserInterface) context.getElement("userService");
        userService.test();
    }
}
