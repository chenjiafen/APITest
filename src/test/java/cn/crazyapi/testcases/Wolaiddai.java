package cn.crazyapi.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;


import cn.crazyapi.http.HttpMethod;
import cn.crazyapi.http.HttpRequest;
import cn.crazyapi.http.HttpResponse;

public class Wolaiddai {
    String host = "japi-fat.wolaidai.com";
    String mobile = "18567672342";
    String cipherData = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArSYgIwg7ydRL/Cmip6bJw4QRDjphcIJYOjMjHwJ2dMM75GOcGzf7hF7afRxoujVGxTXaGKwEM5Tmhw5LVL3H8W3HBYksmiKets9p4FjFwkZNJ1FXNSJiw9rv1OBqRjyjN6aJ+RK50icUUtsvNh/Mq1H1Tkk85ioCyo3NoELphe1a33UOQ/mRPAGcdB3k2tJ4ZNa2C8aOSn1FaRrmqYqRek4magyo6T8AU486rEQuVRiPH1LoQFp5bOG3xrthA1e3u1eKE3mir0hVVYzWHKflfm5VtQafegcV+tCA/aRP5jWEG1s5KWO9Wi2n7uI+4ldPrkPHeJKzhR9sgSuoG88suQIDAQAB";
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
    String oriData = mobile + "&" + timestamp + "&" + cipherData;
    String sign = jimi.md5(oriData);
    String origin = "yingyongbao";
    String token;

    /**
     * 短信发送
     */

    @Test
    public void test001_code() {
        HttpRequest req = new HttpRequest().method(HttpMethod.GET)// 请求方式是get还是post
                .host(host)
                .path("/jrocket2/api/v3/sessions/" + mobile + "?" + "timestamp="
                        + timestamp + "&" + "sign=" + sign)// 请求的接口地址
                .header("x-partner-code", "wld").header("x-product-code", "WLD")
                .header("x-source-id", "1").header("x-origin", "yingyongbao")
                .header("x-app-version", "5.2.3")
                .header("Content-Type", "application/json");

        HttpResponse rep = req.send();
        System.out.println(rep.statusLine());
        String reponseResult = rep.body();
        JSONObject jsonResult = JSONObject.parseObject(reponseResult);
        System.out.println(reponseResult);
        Assert.assertEquals(200, rep.statusCode());
    }

    /**
     * 登录
     */
    @Test
    public void test002_login() {
        String parm = "{\"mobile\":\"" + mobile
                + "\",\"otp\":\"888888\",\"refereeCode\":\"\",\"origin\":\""
                + origin + "\"}";
        HttpRequest req = new HttpRequest().method(HttpMethod.POST).host(host)

                .path("/jrocket2/api/v2/sessions")
                .header("x-partner-code", "wld").header("x-product-code", "WLD")
                .header("x-source-id", "1").header("x-origin", origin)
                .header("x-app-version", "5.2.3")
                .contentType("application/json").data(parm);

        HttpResponse res = req.send();
        String body = res.body();
        JSONObject json = JSONObject.parseObject(body);
        System.out.println("fasdfaf1" + json);
        token = json.getString("X-User-Token");
        Assert.assertEquals(200, res.statusCode());

    }
}
