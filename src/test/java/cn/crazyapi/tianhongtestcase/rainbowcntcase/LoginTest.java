package cn.crazyapi.tianhongtestcase.rainbowcntcase;

import cn.crazyapi.Constans.RainbowcnCrm;
import cn.crazyapi.common.ResultEnum;
import cn.crazyapi.http.HttpMethod;
import cn.crazyapi.http.HttpRequest;
import cn.crazyapi.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.qameta.allure.Description;
import io.qameta.allure.Features;
import io.qameta.allure.Stories;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author chenjiafneg
 * @Date 2020/7/28 13:52
 * @Version 1.0
 */
public class LoginTest extends TestBase {

    @Test(dataProvider = "UserData")
    public void LoginTest(String name, String pass) {
        Map<String, Object> map = new HashMap<>();
        map.put("account", name);
        map.put("password", pass);
        String parm = JSON.toJSONString(map);
        HttpResponse reqs = request.method(HttpMethod.POST).host(RainbowcnCrm.Base_URL_SIT)
                .path(RainbowcnCrm.Rainbowcn_Logind_URL)
                .contentType("application/json; charset=utf-8").data(parm).send();
        String reponseResult = reqs.body();
        JSONObject jsonResult = JSONObject.parseObject(reponseResult);
        String username = jsonResult.getJSONObject("data").getJSONObject("user").getString("jobNum");
        String token = jsonResult.getJSONObject("data").getString("token");
        super.setToken(token);
        log.info("username===>>" + username + "======>>" + "token:" + token + "======>>" + "\n\t" + "jsonResult===>>" + jsonResult);
        Assert.assertEquals(map.get("account"), username);

    }
    @Test(dependsOnMethods = "LoginTest")
    @Description("获取所有生命周期事件")
    public void EventAllTest() {
        HttpRequest request=setHeader();
        HttpRequest req =request.method(HttpMethod.GET).host(RainbowcnCrm.Base_URL_SIT).path(RainbowcnCrm.Rainbowcn_EventAll);
        HttpResponse response = req.send();
        String body = response.body();
        String message = JSONObject.parseObject(body).getString("message");
        log.info("body:" + body+"======>"+"message"+message);
        Assert.assertEquals(ResultEnum.SUCCESS.getMsg(), message);

    }


}
