import entity.Car;
import entity.Emp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_DI.xml")
public class DITest {
    @Autowired
    private Emp emp;
    @Test
    public void test(){
        System.out.println(emp);
    }
    @Test
    public void test2(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext_DI.xml");
        Emp emp = (Emp) context.getBean("emp");
        System.out.println("test2");
        System.out.println(emp);
    }
    @Test
    public void test3(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext_DI.xml");
        Emp emp2 = (Emp) context.getBean("emp2");
        System.out.println(emp2);
    }
}
