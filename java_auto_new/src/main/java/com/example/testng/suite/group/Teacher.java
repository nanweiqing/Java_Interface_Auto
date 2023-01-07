package com.example.testng.suite.group;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class Teacher {

    public void teach() {
        System.out.println("老师在教学");
    }
}
