import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class Demo7 {
    @Test
    public void test(){
        // 创建 Set<HostAndPort> nodes
        Set<HostAndPort> nodes=new HashSet<>();
        nodes.add(new HostAndPort("192.168.2.123",7001));
        nodes.add(new HostAndPort("192.168.2.123",7002));
        nodes.add(new HostAndPort("192.168.2.123",7003));
        nodes.add(new HostAndPort("192.168.2.123",7004));
        nodes.add(new HostAndPort("192.168.2.123",7005));
        nodes.add(new HostAndPort("192.168.2.123",7006));

        //创建 JedisCluster 对象
        JedisCluster jc=new JedisCluster(nodes);
        // 操作
        String value = jc.get("a");
        System.out.println("value:"+value);
    }
}
