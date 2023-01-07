package com.restassured;

import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RestAssuredDemo {

    @Test
    public void firstGetRequest() {
       //设置请求头，参数
        given().
                when().
                get("https://www.baidu.com").then().log().body();
    }

    @Test
    public void getDemo01() {
        given().
                queryParam("name","1242").
                queryParam("pwd","we34").
        when().
                get("http://www.httpbin.org/get").
        then().
                log().body();
    }

    @Test
    public void getDemo02() {
        given().
                formParam("name","1242").
                formParam("pwd","we34").
                //对于form表单，可写可不写，restassured框架会自动检测添加
                contentType("application/x-www-form-urlencoded").
                when().
                post("http://www.httpbin.org/post").
                then().
                log().body();
    }

    @Test
    public void getDemo03() {
        String jsonData = "{\"name\":\"123\",\"pwd\":\"3434\"}";
        given().
                body(jsonData).
                //对于form表单，可写可不写，restassured框架会自动检测添加
                contentType("application/json").
                when().
                post("http://www.httpbin.org/post").
                then().
                log().body();
    }

    @Test
    public void getDemo04() {
        String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<ad ver=\"4\">\n" +
                "\t<count>0</count>\n" +
                "\t<color>#FF0000</color>\n" +
                "\t<url autologin=\"1\">http://pv.7fgame.com/PVCount.aspx?id=1875</url>\n" +
                "</ad>";
        given().
                body(xmlData).
                //对于form表单，可写可不写，restassured框架会自动检测添加
                        contentType("application/xml").
                when().
                post("http://www.httpbin.org/post").
                then().
                log().body();
    }

    @Test
    public void getDemo05() {
        String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<ad ver=\"4\">\n" +
                "\t<count>0</count>\n" +
                "\t<color>#FF0000</color>\n" +
                "\t<url autologin=\"1\">http://pv.7fgame.com/PVCount.aspx?id=1875</url>\n" +
                "</ad>";
        given().
                multiPart(new File("E:\\editplus\\codepage.txt")).
                when().
                post("http://www.httpbin.org/post").
                then().
                log().body();
    }

    //获取响应，log().all()将结果控制台输出
    @Test
    public void getDemo06() {
        String jsonData = "{\"mobile_phone\":\"13323231111\",\"pwd\":\"12345678\"}";
        Response response =
                given().
                        body(jsonData).
                        header("Content-Tpye", "application/json").header("X-Lemonban-Media-Type","lemonban.v1").
                when().
                post("http://api.lemonban.com/futureloan/member/login").
                then().
//                    extract().response();
                log().all().extract().response();
        String s = response.jsonPath().get("msg");
        System.out.println("json解析结果为:"+s);
        System.out.println("接口响应时间:"+response.time());
        System.out.println("headerTou："+response.getHeader("Content-Type"));
    }


    @Test
    public void getDemo07() {
        Response response =
                given().
                        when().
                        get("http://www.httpbin.org/json").
                        then().
//                    extract().response();
        log().all().extract().response();
//        List<String> s = ;
//        System.out.println("提取的结果:"+s.get(1));
        List<String> ls = response.jsonPath().getList("slideshow.slides.title");
        System.out.println(ls.get(0));

    }
}
