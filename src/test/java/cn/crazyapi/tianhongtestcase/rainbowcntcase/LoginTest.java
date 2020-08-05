package cn.crazyapi.tianhongtestcase.rainbowcntcase;

import cn.crazyapi.Constans.Constans;
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
        HttpRequest request = setHeader();
        HttpRequest req = request.method(HttpMethod.GET).host(RainbowcnCrm.Base_URL_SIT).path(RainbowcnCrm.Rainbowcn_EventAll_URL);
        HttpResponse response = req.send();
        String body = response.body();
        String message = JSONObject.parseObject(body).getString("message");
        log.info("body:" + body + "======>" + "message" + message);
        Assert.assertEquals(ResultEnum.SUCCESS.getMsg(), message);

    }

    @Test(dependsOnMethods = "LoginTest")
    @Description("生命周期创建")
    public void SaveAndSubmit() {
        String parm = "{\n" +
                "\t\"activityName\": \"会员生日\",\n" +
                "\t\"channelList\": [{\n" +
                "\t\t\"channel\": 2,\n" +
                "\t\t\"templateMessageCode\": \"0022|0044|0066\"\n" +
                "\t}],\n" +
                "\t\"integral\": 10,\n" +
                "\t\"couponTempCodes\": [],\n" +
                "\t\"eventCode\": \"BARTHID\",\n" +
                "\t\"storeCodeList\": [\"C011_00195\", \"C001_0055_00601\", \"C001_0055_00602\", \"C001_0055_00603\", \"C001_0055_01401\", \"C001_0055_01403\", \"C001_0055_00605\", \"C001_0056_01201\", \"C001_0056_01601\", \"C001_0056_01602\", \"C001_0056_03801\", \"C001_0053_00305\", \"C001_0053_00306\", \"C001_0053_00307\", \"C001_0053_00308\", \"C001_0053_00309\", \"C001_0053_00310\", \"C001_0053_00311\", \"C001_0053_03401\", \"C001_0054_01501\", \"C001_0054_01503\", \"C001_0054_01504\", \"C001_0054_04101\", \"C001_0054_03001\", \"C001_0054_04301\", \"C001_0031_00166\", \"C001_0031_06501\", \"C001_0008_04201\", \"C001_0057_01702\", \"C001_0057_01701\", \"C001_0057_02402\", \"C001_C010_00133\", \"C001_C010_04501\", \"C001_C010_01101\", \"C001_0051_02301\", \"C001_0051_00119\", \"C001_0051_00120\", \"C001_0051_00121\", \"C001_0051_00123\", \"C001_0051_00124\", \"C001_0051_00125\", \"C001_0051_00126\", \"C001_0051_00127\", \"C001_0051_00129\", \"C001_0051_00130\", \"C001_0051_00131\", \"C001_0051_02204\", \"C001_0051_01102\", \"C001_0051_01103\", \"C001_0051_01104\", \"C001_0051_00142\", \"C001_0051_00202\", \"C001_0051_00103\", \"C001_0051_00104\", \"C001_0051_00107\", \"C001_0051_00109\", \"C001_0051_00110\", \"C001_0051_00112\", \"C001_0051_00113\", \"C001_0051_00114\", \"C001_0051_00115\", \"C001_0051_00116\", \"C001_0051_00117\", \"C001_0051_00118\", \"C001_0051_00190\", \"C001_0052_01002\", \"C001_0052_01003\", \"C001_0052_01004\", \"C001_0052_01006\", \"C001_0052_06101\", \"C002_0021_00132\", \"C002_0021_03301\", \"C002_0021_00128\", \"C002_0021_00314\", \"C002_0021_07201\", \"C002_0021_00139\", \"C002_0021_00143\", \"C002_0022_01902\", \"C003_0042_93002\", \"C003_0042_93003\", \"C003_0042_93031\", \"C003_0042_93028\", \"C003_0042_93032\", \"C003_0042_83011\", \"C003_0042_83013\", \"C003_0041_82323\", \"C003_0041_92072\", \"C003_0041_82316\", \"C003_0041_92092\", \"C003_0041_98001\", \"C003_0041_82057\", \"C003_0041_82062\", \"C003_0041_82063\", \"C003_0041_90048\", \"C003_0041_82321\", \"C003_0041_90051\", \"C003_0041_82331\", \"C003_0041_80001\", \"C003_0041_99001\", \"C003_0041_90027\", \"C003_0041_80002\", \"C003_0041_82312\", \"C003_0041_80111\", \"C003_0041_90143\", \"C003_0041_99105\", \"C003_0041_90149\", \"C003_0041_90134\", \"C003_0041_90135\", \"C003_0041_90144\", \"C003_0041_90146\", \"C003_0041_90148\", \"C003_0041_90151\", \"C003_0041_90130\", \"C003_0041_90138\", \"C003_0041_99103\", \"C003_0041_90142\", \"C003_0041_90145\", \"C003_0041_90107\", \"C003_0041_90108\", \"C003_0041_90109\", \"C003_0041_90110\", \"C003_0041_90111\", \"C003_0041_90113\", \"C003_0041_90153\", \"C003_0041_90102\", \"C003_0041_90103\", \"C003_0041_80104\", \"C003_0041_80106\", \"C003_0041_90123\", \"C003_0041_80107\", \"C003_0041_80108\", \"C003_0041_90124\", \"C003_0041_80109\", \"C003_0041_90121\", \"C003_0041_80105\", \"C003_0041_90118\", \"C003_0041_90155\", \"C003_0041_92071\", \"C003_0041_92074\", \"C003_0041_82311\", \"C003_0041_82315\", \"C003_0041_99004\", \"C003_0041_92172\", \"C003_0041_90152\", \"C003_0041_90139\", \"C003_0041_90131\", \"C003_0041_99102\", \"C003_0041_90136\", \"C003_0041_90137\", \"C003_0041_90147\", \"C003_0041_90120\", \"C003_0041_99101\", \"C003_0041_90125\", \"C003_0041_90126\", \"C003_0041_90127\", \"C003_0041_90154\", \"C003_0041_80103\", \"C003_0041_90116\", \"C003_0041_90117\", \"C003_0041_90119\", \"C003_0041_92171\", \"C003_0041_92174\", \"C003_0041_90129\", \"C003_0041_90132\", \"C003_0041_90133\", \"C003_0041_90140\", \"C003_0041_90141\", \"C003_0041_92175\", \"C003_0041_90128\", \"C003_0041_80110\", \"C003_0041_99104\", \"C000_0071_00109\", \"C000_0071_00110\", \"C000_0071_02301\", \"C000_0071_02204\", \"C000_0071_66002\", \"C000_0071_60001\", \"C000_0071_60006\", \"C000_0071_00202\", \"C000_0071_00103\", \"C000_0071_00104\", \"C000_0071_00107\", \"C000_0071_00112\", \"C000_0071_00113\", \"C000_0071_00114\", \"C000_0071_00115\", \"C000_0071_00116\", \"C000_0071_00117\", \"C000_0071_00118\", \"C000_0071_00119\", \"C000_0071_00121\", \"C000_0071_00123\", \"C000_0071_00124\", \"C000_0071_00125\", \"C000_0071_00126\", \"C000_0071_00127\", \"C000_0071_00128\", \"C000_0071_00129\", \"C000_0071_00130\", \"C000_0071_00131\", \"C000_0071_00132\", \"C000_0071_00133\", \"C000_0071_00139\", \"C000_0071_71001\", \"C000_0072_01002\", \"C000_0072_01003\", \"C000_0072_01004\", \"C000_0072_01101\", \"C000_0072_01102\", \"C000_0072_01103\", \"C000_0072_01104\", \"C000_0072_06101\", \"C000_0072_04501\", \"C000_0072_66001\", \"C000_0072_60002\", \"C000_0072_60003\", \"C000_0072_60004\", \"C000_0072_60005\", \"C000_0072_01006\", \"C000_0004_01501\", \"C000_0004_01503\", \"C000_0004_01504\", \"C000_0004_00305\", \"C000_0004_00306\", \"C000_0004_00307\", \"C000_0004_00308\", \"C000_0004_00309\", \"C000_0004_00310\", \"C000_0004_00311\", \"C000_0004_00314\", \"C000_0004_03001\", \"C000_0004_03301\", \"C000_0004_03401\", \"C000_0004_04101\", \"C000_0004_04301\", \"C000_0004_07201\", \"C000_0004_63002\", \"C000_0006_01701\", \"C000_0006_02402\", \"C000_0003_00601\", \"C000_0003_00602\", \"C000_0003_00603\", \"C000_0003_00605\", \"C000_0003_62001\", \"C000_0005_01601\", \"C000_0005_01602\"],\n" +
                "\t\"triggerMax\": 10,\n" +
                "\t\"triggerPhone\": \"17688732017\"\n" +
                "}";
        HttpRequest request = setHeader();
        HttpRequest req = request.method(HttpMethod.POST).host(RainbowcnCrm.Base_URL_SIT).path(RainbowcnCrm.SaveAndSubmit_URL)
                .contentType("application/json; charset=utf-8").data(parm);
        HttpResponse response=  req.send();
        String body = response.body();
        JSONObject jsonResult = JSONObject.parseObject(body);
        String s=jsonResult.getString("message");
        log.info("响应体："+body+"====>"+s);
        Assert.assertEquals(ResultEnum.SAVE_FAIL.getMsg(), s);

    }

    @Test(dependsOnMethods = "LoginTest")
    public void audit(){
        HttpRequest request = setHeader();
        String parm="{\n" +
                "\t\"id\": \"27\",\n" +
                "\t\"result\": 0,\n" +
                "\t\"reason\": \"不通过\"\n" +
                "}";
        HttpRequest req = request.method(HttpMethod.POST).host(RainbowcnCrm.Base_URL_SIT).path(RainbowcnCrm.Audit_URL)
                .contentType("application/json; charset=utf-8").data(parm);
        HttpResponse response= req.send();
        String body = response.body();
        JSONObject jsonResult = JSONObject.parseObject(body);
        String message=jsonResult.getString("message");
        log.info("响应体："+body+"====>"+message);
        Assert.assertEquals(ResultEnum.SUCCESS.getMsg(), message);
    }
}
