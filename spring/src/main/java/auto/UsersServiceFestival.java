package auto;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component // 自动装配的歧义性 3.默认的id为首字母小写的类名
@Component("ff")
@Qualifier("Festival")//自动装配的歧义性 2.使用限定符
public class UsersServiceFestival implements UserService{
    @Override
    public void add() {
        System.out.println("UsersServiceFestival add()");
    }
}
