package com.iiikn.service;

import com.iiikn.annotaion.Configuration;
import com.iiikn.annotaion.Element;

@Configuration
public class ConfigruationTest {

    @Element("test")
    public Test userService2() {
        return new Test();
    }
}
