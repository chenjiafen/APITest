package cn.crazyapi.tianhongtestcase.yongwangcase;

import cn.crazyapi.Constans.Constans;
import cn.crazyapi.common.ResultEnum;
import cn.crazyapi.http.HttpMethod;
import cn.crazyapi.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import org.slf4j.Logger;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SendSms extends TestBase {
    //    Logger logger = LoggerFactory.getLogger(getClass());

    @Test(dataProvider = "sendDatas")
    @Description("发送短信消息")
    public void testSend(String mobile) {
        Map<String, Object> map = new HashMap<>();
        map.put("appid", "app");
        map.put("mobile", mobile);
        map.put("smsTemplateType", "4");
        String basicInfoJsonStr = JSON.toJSONString(map);
        HttpResponse reqs = request.method(HttpMethod.POST).host(Constans.Base_URL_DIV)
                .path(Constans.SendSms_URL)
                .data(basicInfoJsonStr).send();
        String reponseResult = reqs.body();
        JSONObject jsonResult = JSONObject.parseObject(reponseResult);
        String code = jsonResult.getString("code");
        log.info("响应体" + reponseResult + "响应code" + code + "statusLine" + reqs.statusLine());
        Assert.assertEquals(ResultEnum.SUCCESS_MESSAGE.getMsg(), jsonResult.getString("message"));
    }

    @DataProvider
    public Object[][] sendDatas() {
        Object[][] datas = {
                {"18566582390"},
                {"17688732017"},
        };
        return datas;
    }
}
