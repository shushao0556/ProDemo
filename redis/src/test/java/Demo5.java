import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

public class Demo5 {
    @Test
    public void pipeline(){
        //1. 创建连接池
        JedisPool pool=new JedisPool("192.168.2.123",6379);
        long l=System.currentTimeMillis();
        //2. 获取一个连接对象
        Jedis jedis = pool.getResource();
        //3. 创建管道
        Pipeline pipelined = jedis.pipelined();
        //3.1 执行incr - 100000次放到管道中
        for(int i=0;i<100000;i++){
            pipelined.incr("qq");
        }
        //4. 执行命令
        pipelined.syncAndReturnAll();
        //5. 释放资源
        jedis.close();
        System.out.println(System.currentTimeMillis()-l);
    }
}
