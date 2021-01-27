package auto;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
//设置扫描组件的基础包
//@ComponentScan("包名")
//@ComponentScan(basePackages = {"包名","包名1"})
//@ComponentScan(basePackages = {"包名","包名1"})
//@ComponentScan(basePackageClasses = {类名.class,类名1.class})
public class AppConfig {
    public static void main(String[] args) {
        //ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        ApplicationContext context=new AnnotationConfigApplicationContext(App.class);
    }
}
