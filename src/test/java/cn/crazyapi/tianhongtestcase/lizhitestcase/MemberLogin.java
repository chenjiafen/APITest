package cn.crazyapi.tianhongtestcase.lizhitestcase;

import cn.crazyapi.Constans.Constans;
import cn.crazyapi.common.ResultEnum;
import cn.crazyapi.http.HttpMethod;
import cn.crazyapi.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/**
 * @Author chenjiafneg
 * @Date 2020/7/15 11:38
 * @Version 1.0
 */
public class MemberLogin extends TestBase {

    String token;

    @Test(dataProvider = "loginDatas")
    @Description("cmr登录接口测试不同账号")
//    @Step("登录")
    public void testLogin(String username, String password) {
        String parm = "{\n" +
                "\t\"deviceRequest\": {\n" +
                "\t\"deviceCode\": \"4.4.2\",\n" +
                "\t\"deviceName\": \"M688C\",\n" +
                "\t\"deviceToken\": \"71b73cb35946ada509a181e780409638\",\n" +
                "\t\"deviceUid\": \"71b73cb35946ada509a181e780409638\",\n" +
                "\t\"deviceType\": 2\n" +
                "\t},\n" +
                "\t\"ip\": \"172.16.152.15\",\n" +
                "\t\"latitude\": \"36.056967\",\n" +
                "\t\"longitude\": \"120.397783\",\n" +
                "\tpassword: \"" + password + "\",\n" +
                "\t\"username\": \"" + username + "\"\n" +
                "}";
        HttpResponse reqs = request.method(HttpMethod.POST).host(Constans.Base_URL)
                .path(Constans.Login_UR)
                .contentType("application/json").data(parm).send();
        System.out.println(reqs.statusLine());
        String reponseResult = reqs.body();
        JSONObject jsonResult = JSONObject.parseObject(reponseResult);
        String message = jsonResult.getString("message");
        token = jsonResult.getJSONObject("data").getString("token");
//        if (token == null && token == " ") {
//            logger.info("登录失败");
//        }
        System.out.println(reponseResult);
        Assert.assertEquals(ResultEnum.SUCCESS.getMsg(), message);


    }

    @DataProvider
    public Object[][] loginDatas() {
        Object[][] datas = {
                {"18566582390", "chen980985672"}
        };
        return datas;
    }

}
