package auto;

import dao.UsersDao;
import dao.impl.UsersDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@ComponentScan
@Configuration
@ContextConfiguration("classpath:applicationContext.xml")
public class App {
    @Bean //通过javaConfig配置bean对象
    public UsersDao getUsersDao(){
        System.out.println("创建 UsersDao 对象");
        return new UsersDaoImpl();
    }
    @Bean //通过构造方法配置 bean 对象
    public UsersDao getUsersD(UsersDao ud){
        System.out.println("通过构造方法创建UsersService对象");
        return new UsersDaoImpl(ud);
    }
    /*@Bean//通过setter方法配置 bean 对象
    public UsersDao getSetUsersDao(UsersDao ud){
        System.out.println("通过setter方法创建UsersService对象");
        UsersDaoImpl udi=new UsersDaoImpl();
        udi.setUd(ud);
        return udi;
    }*/

}
