package cn.crazyapi.wolaidaitestcast;

import cn.crazyapi.testcases.jimi;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;


import cn.crazyapi.http.HttpMethod;
import cn.crazyapi.http.HttpRequest;
import cn.crazyapi.http.HttpResponse;

public class Wolaiddai extends TestBase {

    /**
     * 短信发送
     */

    @Test
    public void test001_code() {
        HttpResponse req = request.method(HttpMethod.GET)// 请求方式是get还是post
                .host(host)
                .path("/jrocket2/api/v3/sessions/" + mobile + "?" + "timestamp="
                        + timestamp + "&" + "sign=" + sign)// 请求的接口地址
                .send();
        System.out.println(req.statusLine());
        String reponseResult = req.body();
        JSONObject jsonResult = JSONObject.parseObject(reponseResult);
        System.out.println(reponseResult);
    }

    /**
     * 登录
     */
    @Test
    public void test002_login() {
        String parm = "{\"mobile\":\"" + mobile
                + "\",\"otp\":\"888888\",\"refereeCode\":\"\",\"origin\":\""
                + origin + "\"}";
        HttpRequest req = request.method(HttpMethod.POST).host(host)
                .path("/jrocket2/api/v2/sessions")
                .contentType("application/json").data(parm);
        HttpResponse res = req.send();
        String body = res.body();
        JSONObject json = JSONObject.parseObject(body);
        log.info("code_json" + json);
        token = json.getString("X-User-Token");
        log.info("token:" + token);
        super.setToken(token);
        log.info("getToke" + super.getToken());
        Assert.assertEquals(200, res.statusCode());

    }

    @Test
    public void test003_configList() {
        HttpRequest req = request.method(HttpMethod.GET).host(host)
                .path("/marketing/api/url/config/list");
        HttpResponse res = req.send();
        String body = res.body();
        JSONObject json = JSONObject.parseObject(body);
        log.info("configList_json" + json);
        Assert.assertEquals(200, res.statusCode());
    }


    @Test
    public void test004_liaisons() {
        log.info(" base.getToken();" + super.getToken());
        String parm = "[{\"name\":\"杨阳洋\",\"mobile\":\"18566786642\",\"relationship\":\"parents\"}]";
        HttpRequest req = request.method(HttpMethod.POST).host(host)
                .path("/apply-gateway/api/wld/APP-JDD/appCompanyInfo")
                .contentType("application/json").data(parm);
        HttpResponse res = req.send();
        String body = res.body();
        JSONObject json = JSONObject.parseObject(body);
        System.out.println("json：" + json);
        Assert.assertEquals(200, res.statusCode());

    }

    @Test
    public void test005_liaisons(){

    }


}
