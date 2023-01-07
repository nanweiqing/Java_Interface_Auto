package com.restassured.base;

import com.alibaba.fastjson.JSON;
import com.restassured.data.Constants;
import com.restassured.pojo.ExcelPojo;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseTest {

    public Response request(ExcelPojo excelPojo) {

        String url = Constants.BASE_URI+excelPojo.getUrl();
        Map<String ,Object> headerMap = JSON.parseObject(excelPojo.getRequestHeader(),Map.class);
        Response response = null;
        if("GET".equals(excelPojo.getMethod())) {
            response = given().headers(headerMap)
                    .when().get(url).
                    then().log().all().extract().response();
        }else if("POST".equals(excelPojo.getMethod())) {
            response = given().headers(headerMap).
                            body(excelPojo.getInputParams())
                    .when().post(url).
                    then().log().all().extract().response();
        }
        return  response;
    }


}
