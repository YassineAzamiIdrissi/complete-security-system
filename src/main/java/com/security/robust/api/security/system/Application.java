package com.security.robust.api.security.system;

import com.security.robust.api.security.system.Authority.Authority;
import com.security.robust.api.security.system.Authority.AuthorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    @Bean
    public CommandLineRunner commandLineRunner(AuthorityRepository repo) {
        return args -> {
            if (repo.findByAuthority("USER").isEmpty()) {
                repo.save(Authority.builder().authority("USER").build());
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
