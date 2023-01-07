package com.example.testng.suite.group;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class Student1 {

    public void stu() {
        System.out.println("学生1在上课");
    }
}
