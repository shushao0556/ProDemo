import auto.AppConfig;
import auto.CDPlayer;
import auto.UsersServiceFestival;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import auto.UserService;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AppTest {
    @Autowired
    private CDPlayer player;
    @Autowired
    private UserService us;
    @Autowired
    //@Qualifier("Festival") //自动装配的歧义性 3.在装配的时候使用@Qualifier
    @Resource(name = "ff") // 4.使用@Resource注解替换Autowired和限定符
    private UsersServiceFestival uf;
    @Test
    public void test(){
        player.play();
    }
    @Test
    public void test2(){
        us.add();
        uf.add();
    }
}
