package cn.crazyapi.tianhongtestcase.crmtestcase;

import cn.crazyapi.http.HttpRequest;
import cn.crazyapi.tianhongtestcase.yongwangtestcase.SendSms;
import cn.crazyapi.util.ExcelUtil;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.logging.Logger;

public class TestBase {
    HttpRequest request;
    Logger log = Logger.getLogger(SendSms.class.getName());
    private  String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TestBase() {
        this.request = setHeader();
    }

    public HttpRequest setHeader() {

        HttpRequest req = new HttpRequest()
                .header("x-http-token", token)
                .header("x-http-screenwidth", "720")
                .header("x-http-encryption", " 0")
                .header("j-http-devicetoken", "160a3797c884ef84a32")
                .header("x-http-devicetoken", "160a3797c884ef84a32")
                .header("x-http-channel", "app")
                .header("x-http-osversion", "5.1.1")
                .header("x-http-devicetype", "android")
                .header("x-http-version", "1.1.5.8")
                .header("x-http-deviceuid", "160a3797c884ef84a32")
                .header("Content-Typet", "application/json;charset=UTF-8");
        return req;

    }
    @BeforeClass
    public void BeforeClass() {
        String fileName = this.getClass().getClassLoader().getResource("log4j.xml").getPath();
        DOMConfigurator.configure(fileName);
    }

}
