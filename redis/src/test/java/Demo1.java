import org.junit.Test;
import redis.clients.jedis.Jedis;

public class Demo1 {
    @Test
    public void set(){
        //1.连接 Redis
        Jedis jedis=new Jedis("192.168.2.123",6379);
        //2. redis 密码
        jedis.auth("redis");
        //3.操作 Redis--redis命令是什么，Jedis 命令就是什么
        jedis.set("name","李四");
        //4.释放资源
        jedis.close();
    }
    @Test
    public void get(){
        //1.连接 Redis
        Jedis jedis=new Jedis("192.168.2.123",6379);
        //2.操作 Redis--redis命令是什么，Jedis 命令就是什么
        String name = jedis.get("name");
        System.out.println(name);
        //3.释放资源
        jedis.close();
    }
}
