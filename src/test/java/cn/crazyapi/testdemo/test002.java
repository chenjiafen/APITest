package cn.crazyapi.testdemo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class test002 {

    @DataProvider(name="provider")
    public Object[][] provider(){
        Object [][] provider = new Object [5][2];
        for (int i = 0; i < provider.length; i++) {
            provider[i][0] = "name"+i;
            provider[i][1] = i+10;
        }

        return provider;
    }
    @Test(dataProvider="provider")
    public void getName(String name,int age){
        System.out.println(name+"_"+age);

    }

    @DataProvider
    public Object[][] sendDatas() {
        Object[][] datas = {
                {"app","18566582390","4"},
                {"app","18566582391","5"},
                {"app","18566582392","6"},
                {"app","18566582393","7"},
                {"app","18566582394","8"},
                {"app","18566582395","9"},
                {"app","18566582396","0"},



        };
        return datas;
    }

    @Test(dataProvider="sendDatas")
    public void getsendDatas(String appid,String mobile,String type){
        System.out.println(appid+"_"+mobile+"_"+type);

    }
    @Test
    public  void test01(){
        String fileName = this.getClass().getClassLoader().getResource("tianhong.xlsx").getPath();//获取文件路径
        String fileName1 = this.getClass().getClassLoader().getResource("log4j.xml").getPath();//获取文件路径
        System.out.println(fileName);
    }

}
