package demo.entity;

import demo.components.AppLocaleResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private User user;

    @Test
    public void test(){
        System.out.println("user -> " + user);

        System.out.println(System.getenv() + "\n");

        System.out.println(System.getProperties());

        AppLocaleResolver applocalResolver = beanFactory.getBean(AppLocaleResolver.class);

        System.out.println(applocalResolver);
    }
}
