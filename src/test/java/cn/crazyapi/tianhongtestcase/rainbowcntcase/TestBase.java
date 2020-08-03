package cn.crazyapi.tianhongtestcase.rainbowcntcase;

import cn.crazyapi.http.HttpRequest;
import cn.crazyapi.tianhongtestcase.yongwangcase.SendSms;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.logging.Logger;

/**
 * @Author chenjiafneg
 * @Date 2020/7/17 14:08
 * @Version 1.0
 */
public class TestBase {
    HttpRequest request = setHeader();;
    Logger log = Logger.getLogger(TestBase.class.getName());
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

//    public TestBase() {
//        this.request = setHeader();
//    }

    public HttpRequest setHeader() {
        HttpRequest req = new HttpRequest()
                .header("Accept", "application/json")
                .header("jt", token)
                .header("Sec-Fetch-Site", "same-origin")
                .header("Sec-Fetch-Mode", "cors");
        return req;

    }


    @DataProvider(name = "UserData")
    public Object[][] loginDatas() {
        Object[][] datas = {
                {"142037", "chen980985672@"}
        };
        return datas;
    }

    @BeforeClass
    public void BeforeClass() {
        String fileName = this.getClass().getClassLoader().getResource("log4j.xml").getPath();
        DOMConfigurator.configure(fileName);
    }

}
