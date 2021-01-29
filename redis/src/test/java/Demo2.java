import entity.User;
import org.junit.Test;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;

import java.util.Date;

public class Demo2 {
    //存储对象，以byte[] 形式存储在redis中
    @Test
    public void setByteArray(){
        //1.连接 Redis
        Jedis jedis=new Jedis("192.168.2.123",6379);
        //2.1 准备key(Sring)-->value(User)
        String key="user";
        User user=new User(1,"张三","男",new Date(),5000.2);
        byte[] byteKey = SerializationUtils.serialize(key);
        byte[] byteValue = SerializationUtils.serialize(user);
        jedis.set(byteKey,byteValue);
        //3.释放资源
        jedis.close();
    }
    @Test
    public void getByteArray(){
        //1.连接 Redis
        Jedis jedis=new Jedis("192.168.2.123",6379);
        //2.1 准备key
        String key="user";
        byte[] byteKey = SerializationUtils.serialize(key);
        byte[] bytesValue = jedis.get(byteKey);
        User user = (User) SerializationUtils.deserialize(bytesValue);
        System.out.println("user:" + user);
        //3.释放资源
        jedis.close();
    }
}
