package cn.crazyapi.testdemo;

import redis.clients.jedis.HostAndPort;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

public class Test001 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File("/Users/Work/crazyapi/src/main/resources/redispool.properties")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        String s=properties.getProperty("redis.ip");
        String[] ww=  s.split(",");

        for (String s1:ww){
            String[] ww1= s1.split(":");
            System.out.println("fasdfaf"+ww1[1]);


            for (String ip:ww1){
                System.out.println(ip);

            }



        }

    }
}
