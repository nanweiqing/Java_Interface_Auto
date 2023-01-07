package com.example.testng.suite;

import org.testng.annotations.Test;

public class DependOnTest {

    @Test
    public void test01() {
        System.out.println("01在执行");
    }

    @Test(dependsOnMethods = {"test01"})
    public void test02() {
        System.out.println("02在执行");
    }
}
