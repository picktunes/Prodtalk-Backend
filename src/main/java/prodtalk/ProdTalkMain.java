package prodtalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "prodtalk.config.CorsConfiguration")
public class ProdTalkMain {

    public static void main(String[] args) {
        SpringApplication.run(ProdTalkMain.class, args);
    }

}
