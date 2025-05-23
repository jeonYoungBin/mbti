package org.com.mbti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpectorAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpectorAssignmentApplication.class, args);
    }

}
