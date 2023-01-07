package com.example.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Testngtest {

    @Test
    public void test02() {
        System.out.println("第2次测试");
    }

    @Test
    public void test01() {
        System.out.println("第一次测试");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("测试方法运行前");
    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("测试方法运行后");
    }


}
