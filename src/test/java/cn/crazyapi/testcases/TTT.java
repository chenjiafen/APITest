package cn.crazyapi.testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import cn.crazyapi.http.HttpRequest;
import cn.crazyapi.http.HttpResponse;

public class TTT {
	
	String host = "https://ali-lottery.showapi.com";
    String path = "/newest";
    String method = "GET";
    String appcode = "3197b0b981be4046954dada7fab40dc1";
	
	@Test
	public void test(){

		HttpRequest req=new HttpRequest().get(host+path).header("Authorization", "APPCODE "+appcode).form("code", "ssq");
		HttpResponse res=req.send();
		String body=res.body();
		System.out.println("法法撒发大发"+res.statusCode());
		System.out.println("xxxx"+body);
	}

}
