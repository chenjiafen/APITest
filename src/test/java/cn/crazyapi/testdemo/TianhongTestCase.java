package cn.crazyapi.testdemo;

import cn.crazyapi.Constans.Constans;
import cn.crazyapi.common.ResultEnum;
import cn.crazyapi.http.HttpMethod;
import cn.crazyapi.http.HttpRequest;
import cn.crazyapi.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class TianhongTestCase {

    //    String username = "18566582390";
//    String password = "chen980985672";
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String token;
    HttpRequest request;

    public TianhongTestCase() {
        this.request = setHeader();
    }

    public HttpRequest setHeader() {
        HttpRequest req = new HttpRequest().header("x-http-channel", "app")
                .header("x-http-devicetoken", "13065ffa4e8d8011841")
                .header("x-http-devicetypen", "android")
                .header("x-http-version", " 1.1.5.8")
                .header("x-http-screenheight", "1920")
                .header("x-http-osversion", "4.4.2")
                .header("x-http-encryption", " 0")
                .header("x-http-deviceuid", "13065ffa4e8d8011841")
                .header("x-http-screenwidth", "1080")
                .header("x-http-screenheight", "1920")
                .header("Content-Typet", "application/json;charset=UTF-8");
        return req;

    }

    @Test(dataProvider = "loginDatas")
    public void testLogin(String username, String password) {
        String parm = "{\n" +
                "\t\"deviceRequest\": {\n" +
                "\t\t\"deviceCode\": \"4.4.2\",\n" +
                "\t\t\"deviceName\": \"M688C\",\n" +
                "\t\t\"deviceToken\": \"71b73cb35946ada509a181e780409638\",\n" +
                "\t\t\"deviceUid\": \"71b73cb35946ada509a181e780409638\",\n" +
                "\t\t\"deviceType\": 2\n" +
                "\t},\n" +
                "\t\"ip\": \"172.16.152.15\",\n" +
                "\t\"latitude\": \"36.056967\",\n" +
                "\t\"longitude\": \"120.397783\",\n" +
                "\tpassword: \"" + password + "\",\n" +
                "\t\"username\": \"" + username + "\"\n" +
                "}";


        HttpResponse reqs = request.method(HttpMethod.POST).host(Constans.Base_URL_SIT)
                .path(Constans.Login_UR)
                .contentType("application/json").data(parm).send();

        System.out.println(reqs.statusLine());
        String reponseResult = reqs.body();
        JSONObject jsonResult = JSONObject.parseObject(reponseResult);
        String message = jsonResult.getString("message");
        System.out.println(reponseResult);
        Assert.assertEquals(ResultEnum.SUCCESS.getMsg(), message);


    }

    @Test(dataProvider = "sendDatas")
    public void testSendSms(String mobile) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("appid", "app");
        map.put("mobile", mobile);
        map.put("smsTemplateType", "4");
        String basicInfoJsonStr = JSON.toJSONString(map);
        HttpResponse reqs = request.method(HttpMethod.POST).host(Constans.Base_URL_SIT)
                .path(Constans.SendSms_URL)
                .data(basicInfoJsonStr).send();
        System.out.println(reqs.statusLine());
        String reponseResult = reqs.body();
        JSONObject jsonResult = JSONObject.parseObject(reponseResult);
        String code = jsonResult.getString("code");
        System.out.println("响应体" + reponseResult);
        System.out.println("响应code" + code);
        System.out.println("reqs.statusLine" + reqs.statusLine());
        Assert.assertEquals(code, reqs.statusLine());


    }

    @DataProvider
    public Object[][] sendDatas() {
        Object[][] datas = {
                {"18566582390"},
                {"17688732107"},
        };
        return datas;
    }

    @DataProvider
    public Object[][] loginDatas() {
        Object[][] datas = {
                {"18566582390", "chen980985672"}
        };
        return datas;
    }
}

