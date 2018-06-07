package pl.coderslab.theultimatebet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.view.tiles3.SpringWildcardServletTilesApplicationContext;
import pl.coderslab.theultimatebet.service.FixtureService;

import javax.swing.*;

@EnableScheduling
@SpringBootApplication
public class TheUltimateBetApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TheUltimateBetApplication.class, args);
        FixtureService fixtureService = context.getBean( FixtureService.class );
        fixtureService.createRoles();
    }
}
