package com.example.testng.suite;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterTest {

    @Test
    @Parameters({"name","age"})
    public void testPara(String name ,int age) {
        System.out.println("名字是:"+name+" 年龄是:"+age);
    }
}
