package com.muke.httpclient;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HttpClientTest {

    @Test
    public void beforeTest() {
        ResourceBundle rb = ResourceBundle.getBundle("application", Locale.CHINA);
        String url = rb.getString("test_url");
        System.out.println("获取到的地址为:"+url);

    }

    @Test
    public void Test01() throws IOException {

        HttpGet get = new HttpGet("http://www.baidu.com");
        //可关闭的httpClient客户端，相当于你打开的浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(get);
        String result = EntityUtils.toString(closeableHttpResponse.getEntity(),"utf-8");
        System.out.println(result);

    }


}
