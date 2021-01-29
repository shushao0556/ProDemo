import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Demo4 {
    @Test
    public void pool(){
        //1. 创建连接池配置信息
        GenericObjectPoolConfig pc=new GenericObjectPoolConfig();
        pc.setMaxTotal(100);//连接池中最大的活跃数
        pc.setMaxIdle(10);//最大空闲数
        pc.setMinIdle(5);//最小空闲数
        pc.setMaxWaitMillis(3000);//当连接池空了以后，多久没获得到jedis对象，就超时
        //2. 创建连接池
        JedisPool jp=new JedisPool("192.168.2.123",6379);
        //3. 通过连接池获取 jedis 对象
        Jedis jedis = jp.getResource();
        //4. 操作
        String value = jedis.get("stringUser");
        System.out.println("User:" + value);
        jp.close();
    }
}
