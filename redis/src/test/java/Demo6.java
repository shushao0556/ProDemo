import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Demo6 {
    @Test
    public void auth(){
        //1. 创建连接池配置信息
        GenericObjectPoolConfig pc=new GenericObjectPoolConfig();
        pc.setMaxTotal(100);//连接池中最大的活跃数
        pc.setMaxIdle(10);//最大空闲数
        pc.setMinIdle(5);//最小空闲数
        pc.setMaxWaitMillis(3000);//当连接池空了以后，多久没获得到jedis对象，就超时
        //2. 创建连接池
        JedisPool jp=new JedisPool(pc,"192.168.2.123",6379,3000,"redis");
        Jedis jedis = jp.getResource();
        jedis.set("name", "admin888");
        jedis.close();
    }
}
