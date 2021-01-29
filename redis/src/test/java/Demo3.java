import com.alibaba.fastjson.JSON;
import entity.User;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Date;

public class Demo3 {
    //存储对象-以String形式存储
    @Test
    public void setString(){
        //1.连接 Redis
        Jedis jedis=new Jedis("192.168.2.123",6379);
        //2.1 准备key(String)-->value(User)
        String stringKey="stringUser";
        User value=new User(2,"admin2","女",new Date(),6666.66);
        //2.2 使用 fastJson 将 value 转化为 json字符串
        String stringValue = JSON.toJSONString(value);
        //2.3 存储到 redis 中
        jedis.set(stringKey,stringValue);
        //3.释放资源
        jedis.close();
    }
    //获取对象-以String形式获取
    @Test
    public void getString(){
        //1.连接 Redis
        Jedis jedis=new Jedis("192.168.2.123",6379);
        //2.1 准备key(String)-->value(User)
        String stringKey="stringUser";
        //2.2 去redis中查询value
        String value = jedis.get(stringKey);
        //2.3 将value反序列化为User
        User user = JSON.parseObject(value,User.class);
        //2.4 输出
        System.out.println("user:" + user);
        //3.释放资源
        jedis.close();
    }
}
