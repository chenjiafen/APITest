package cn.crazyapi.testcases;

import org.apache.commons.codec.digest.DigestUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

import cn.crazyapi.http.CookieStore;
import cn.crazyapi.http.HttpMethod;
import cn.crazyapi.http.HttpRequest;
import cn.crazyapi.http.HttpResponse;

/**
 *
 */
public class XinChangTai {
	
	String token;
	
	@BeforeClass
	public void beforeClass(){
		
	}
	@Test
	public void test001_login(){
//		CookieStore cookie=new CookieStore();
//		cookie.cookie("");
		HttpRequest req=new HttpRequest().method(HttpMethod.POST).host("health.sinoicity.com")
		.path("/HealthMonitor/open/user/login.htm")
		.form("deviceId", "a617b1fa83e30fb2c29486f5d7c74749")
		.form("password", "e99a18c428cb38d5f260853678922e03")
		.form("phone", "18729399607")
		.form("os", "Android")
		.form("type", "12");
		HttpResponse res=req.send();
		String body=res.body();
		JSONObject json=JSONObject.parseObject(body);
		token=json.getString("token");
		System.out.println(json.get("token"));
		
		Assert.assertEquals(json.containsKey("token"), true);
		
	}
	@Test
	public void test002_query(){
		HttpRequest req=new HttpRequest().get("http://health.sinoicity.com/HealthMonitor/patient/user/medicine/plan/query.htm")
			.form("token",token)
			.form("method", "all");
		HttpResponse res=req.send();
		String body=res.body();
		System.out.println("xxxx"+body);
	}
	@Test
	public void test003_addThumbsUp(){
		HttpResponse res=HttpRequest.post("http://health.sinoicity.com/HealthMonitor/article/addThumbsUp.htm")
		.form("token",token)
		.form("articleId", "405").send();
		String body=res.body();
		System.out.println("yyy"+body);
	}
	@AfterClass
	public void afterClass(){
		
	}
	public String JiaMi(String password){
		return DigestUtils.md5Hex(password);
	}
}
