package code.flatura.teamlunch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories("code.flatura.teamlunch.repository")
public class TeamlunchApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(TeamlunchApplication.class, args);

        System.out.println("It works!");
    }
}
