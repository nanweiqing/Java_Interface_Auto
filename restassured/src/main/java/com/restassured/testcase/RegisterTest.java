package com.restassured.testcase;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.restassured.base.BaseTest;
import com.restassured.data.Constants;
import com.restassured.data.Environment;
import com.restassured.pojo.ExcelPojo;
import com.restassured.util.PhoneRandomUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterTest extends BaseTest {

    @BeforeClass
    public void setup() {
        //先生成3条用户名
        String phone1 = PhoneRandomUtil.getUnregisterPhone();
        String phone2 = PhoneRandomUtil.getUnregisterPhone();
        String phone3 = PhoneRandomUtil.getUnregisterPhone();

        Environment.envData.put("phone1", phone1);
        Environment.envData.put("phone2", phone2);
        Environment.envData.put("phone3", phone3);
    }

    @Test(dataProvider = "getAllData")
    public void registTest(ExcelPojo excelPojo){
        //替换每一条用例
        excelPojo = replaceCase(excelPojo);
        Response actual = request(excelPojo);

        String expectStr = excelPojo.getExpected();
        Map<String,Object> expectMap = JSON.parseObject(expectStr, Map.class);
        for(String key : expectMap.keySet()) {
            Object expect = expectMap.get(key);
            Object actuall =  actual.jsonPath().get(key);
            Assert.assertEquals(actuall,expect);
        }
    }


    public ExcelPojo replaceCase(ExcelPojo excelPojo) {
        String inputParams = replaceStr(excelPojo.getInputParams());
        excelPojo.setInputParams(inputParams);
        return excelPojo;
    }


    //替换单条测试用例
    public String replaceStr(String orgStr) {
        String abc= orgStr;
//        String regex = "\\{\\{.*?\\}\\}";
        String regex = "\\{\\{(.*?)}}";
        Pattern p=Pattern.compile(regex);
        Matcher m=p.matcher(abc);
        while(m.find()) {
//            System.out.println(m.group(0));
//            System.out.println(m.group(1));
            String outerStr = m.group(0); //{{phone}}
            System.out.println("外面包裹的:"+outerStr);
            //group(1)表示获取{{}}包裹着的内容
            String innerStr = m.group(1); //phone
            System.out.println("里面包裹的:"+innerStr);
            String replaceStr = (String)Environment.envData.get(innerStr);
            abc = abc.replace(outerStr, replaceStr);
            System.out.println("替换后的字符串为:"+abc);
        }
        return abc;
    }


    public static void main(String[] args) {

    }

    @DataProvider
    public Object[] getAllData() {
        List<ExcelPojo> dataList = readAllExcelData(1);
        Object[] excelPojos = dataList.toArray();
        return excelPojos;
    }




    public List<ExcelPojo> readAllExcelData(int sheetNum){
        File file = new File(Constants.EXCEL_FILE_PATH);
        //导入的参数对象
        ImportParams importParams = new ImportParams();
        //读取第几个sheet
        importParams.setStartSheetIndex(sheetNum-1);
        //读取Excel
        List<ExcelPojo> listDatas = ExcelImportUtil.importExcel(file, ExcelPojo.class,importParams);
        return listDatas;
    }





}
