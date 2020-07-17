package cn.crazyapi.testcases;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class TestProperties {
    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File("/Users/Work/crazyapi/src/main/resources/redispool.properties")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(100);
        // 最大空闲数
        poolConfig.setMaxIdle(25);
        //最大允许等待时间
        poolConfig.setMaxWaitMillis(1000);
        String s = properties.getProperty("redis.ip");
        String[] ww = s.split(",");
        Set<HostAndPort> haps = new HashSet<HostAndPort>();
        for (String s1 : ww) {
            String[] ww1 = s1.split(":");
            HostAndPort hap = new HostAndPort(ww1[0], Integer.parseInt(ww1[1]));
            haps.add(hap);

        }
        JedisCluster cluster = new JedisCluster(haps, poolConfig);
        String name = cluster.get("md:m:memberDomainByMemberId:[58207309582565514]");
        System.out.println(name);

    }
}
