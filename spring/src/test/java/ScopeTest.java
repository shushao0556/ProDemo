import entity.Emp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_scope.xml")
public class ScopeTest {
    @Test
    public void test(){
        AbstractApplicationContext context=new ClassPathXmlApplicationContext("applicationContext_scope.xml");
        Emp emp = (Emp) context.getBean("emp");
        System.out.println(emp);
    }
}
