package cn.crazyapi.wolaidaitestcast;

import cn.crazyapi.http.HttpMethod;
import cn.crazyapi.http.HttpRequest;
import cn.crazyapi.http.HttpResponse;
import cn.crazyapi.testcases.jimi;
import cn.crazyapi.tianhongtestcase.yongwangtestcase.SendSms;
import cn.crazyapi.util.ExcelUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * @Author chenjiafneg
 * @Date 2020/7/20 10:14
 * @Version 1.0
 */
public class TestBase {
    String host = "japi-fat.wolaidai.com";
    String mobile = "18567672342";
    String cipherData = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArSYgIwg7ydRL/Cmip6bJw4QRDjphcIJYOjMjHwJ2dMM75GOcGzf7hF7afRxoujVGxTXaGKwEM5Tmhw5LVL3H8W3HBYksmiKets9p4FjFwkZNJ1FXNSJiw9rv1OBqRjyjN6aJ+RK50icUUtsvNh/Mq1H1Tkk85ioCyo3NoELphe1a33UOQ/mRPAGcdB3k2tJ4ZNa2C8aOSn1FaRrmqYqRek4magyo6T8AU486rEQuVRiPH1LoQFp5bOG3xrthA1e3u1eKE3mir0hVVYzWHKflfm5VtQafegcV+tCA/aRP5jWEG1s5KWO9Wi2n7uI+4ldPrkPHeJKzhR9sgSuoG88suQIDAQAB";
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String oriData = mobile + "&" + timestamp + "&" + cipherData;
    String sign = jimi.md5(oriData);
    String origin = "yingyongbao";
    String token;
    HttpRequest request;
    Logger log = Logger.getLogger(SendSms.class.getName());

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public TestBase() {
        this.request = setHeader();
    }

    public HttpRequest setHeader() {
        HttpRequest req = new HttpRequest().header("x-http-channel", "app")
                .header("x-partner-code", "wld")
                .header("x-product-code", "WLD")
                .header("x-source-id", "1")
                .header("x-origin", "yingyongbao")
                .header("x-app-version", "5.2.3")
                .header("X-User-Token", token)
                .header("Content-Type", "application/json");
        return req;
    }

    @DataProvider
    public Object[][] getDatas(Method method) throws Exception {
        ExcelUtil excel = new ExcelUtil("C:/Users/LXG/Desktop/liecai.xlsx");
        Object[][] result = null;
        if (method.getName().equals("test001_login")) {
            result = excel.getTestData("login");
        } else if (method.getName().equals("test004_search")) {
            result = excel.getTestData("search");
        } else {
            result = new Object[][]{new Object[]{3}};
        }
        return result;
    }

    @BeforeClass
    public void BeforeClass() {
        String fileName = this.getClass().getClassLoader().getResource("log4j.xml").getPath();
        DOMConfigurator.configure(fileName);
    }
}
