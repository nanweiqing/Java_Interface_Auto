package com.example.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("套件测试开始");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("套件测试结束");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("套件测试前");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("套件测试后");
    }
}
