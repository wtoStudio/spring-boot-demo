package demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataSource dataSource;

    @Test
    public void test(){
        System.out.println("userRepository -> " + userRepository.getClass().getName());
    }

    @Test
    public void testLogger(){
        Logger logger = LoggerFactory.getLogger(getClass());

        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");

        System.out.println("dataSource->" + dataSource + "\n" + dataSource.getClass().getName());
    }
}
