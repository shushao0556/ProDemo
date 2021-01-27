package hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan
public class Application {
    public static void main(String[] args) {
        //ApplicationContext context=new AnnotationConfigApplicationContext(Application.class);
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        context.getBean(MessagePrinter.class);
    }
}
