package pl.coderslab.theultimatebet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TheUltimateBetApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheUltimateBetApplication.class, args);
    }
}
