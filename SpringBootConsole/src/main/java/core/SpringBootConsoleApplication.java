package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    private static Logger LOG =LoggerFactory.getLogger(SpringBootConsoleApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       LOG.info("MY SMS");
       LOG.error("MY ERROR");
       LOG.warn("MY ALERT");
    }

}
