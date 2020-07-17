package cn.crazyapi.testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

import cn.crazyapi.http.HttpMethod;
import cn.crazyapi.http.HttpRequest;
import cn.crazyapi.http.HttpResponse;
import cn.crazyapi.util.EncryptionUtil;

/**
 * @Author chenjiafneg
 * @Date 2020/7/15 23:29
 * @Version 1.0
 */

public class TestFan {

    String host = "health.sinoicity.com.cn";
    String token;

    @Test
    public void test001_login() {


        HttpRequest req = new HttpRequest().method(HttpMethod.POST)// 请求方式是get还是post
                .host(host).path("/HealthMonitor/open/user/login.htm")// 请求的接口地址
                .form("deviceId", "a617b1fa83e30fb2c29486f5d7c74749")
                .form("os", "Android")
                .form("password", EncryptionUtil.md5("abc123"))// EncryptionUtil.md5("abc123")
                .form("phone", "18729399607")
                .form("type", "12");
        HttpResponse rep = req.send();
        System.out.println(rep.statusLine());
        String reponseResult = rep.body();
        JSONObject jsonResult = JSONObject.parseObject(reponseResult);
        System.out.println(reponseResult);
        Assert.assertEquals(200, rep.statusCode());
        Assert.assertEquals(true, jsonResult.containsKey("token"));
        token = jsonResult.getString("token");
    }

    @Test(dependsOnMethods = "test001_login")
    public void test002_searchFriend() {
        HttpRequest req = new HttpRequest().method(HttpMethod.POST)// 请求方式是get还是post
                .host(host).path("/HealthMonitor/dynamic/author/info.htm")// 请求的接口地址
                .form("token", token)
                .form("authorType", "2")
                .form("type", "patient")// EncryptionUtil.md5("abc123")
                .form("authorId", "10110644");
        // .form("type", "12");
        HttpResponse rep = req.send();
        System.out.println(rep.statusLine());
        String reponseResult = rep.body();
        JSONObject jsonResult = JSONObject.parseObject(reponseResult);
        Assert.assertEquals(200, rep.statusCode());
        Assert.assertEquals(true, jsonResult.containsKey("data"));
        JSONObject jsonData = jsonResult.getJSONObject("data");
        Assert.assertEquals("18610112903", jsonData.getString("name"));
        Assert.assertEquals("1", jsonData.getString("gender"));
        Assert.assertEquals("186****2903", jsonData.getString("phone"));
        Assert.assertEquals("38", jsonData.getString("age"));
    }


    @Test(dependsOnMethods = "test003_login")
    public void test003_searchFriend() {
        HttpRequest req = new HttpRequest().method(HttpMethod.POST)// 请求方式是get还是post
                .host(host).path("/HealthMonitor/dynamic/author/info.htm")// 请求的接口地址
                .form("token", token)
                .form("authorType", "2")
                .form("type", "patient")// EncryptionUtil.md5("abc123")
                .form("authorId", "10110644");


    }

}
