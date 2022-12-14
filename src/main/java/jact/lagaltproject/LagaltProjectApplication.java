package jact.lagaltproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class LagaltProjectApplication {


    private final Environment env;

    public LagaltProjectApplication(Environment env) {
        this.env = env;
    }
    public static void main(String[] args) {
        SpringApplication.run(LagaltProjectApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:5173", "https://legalt-frontend.pages.dev").allowCredentials(true);
            }
        };
    }

}
