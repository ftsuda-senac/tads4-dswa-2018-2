package br.senac.tads4.dsw.exemplosspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// Para gerar WAR a partir do projeto Spring Boot
// https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-create-a-deployable-war-file
// https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#build-tool-plugins-maven-packaging

@SpringBootApplication
public class ExemplosSpringApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ExemplosSpringApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ExemplosSpringApplication.class, args);
    }
}
