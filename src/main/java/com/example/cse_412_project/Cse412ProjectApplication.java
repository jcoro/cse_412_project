package com.example.cse_412_project;

import com.example.cse_412_project.entities.impl.AppUser;
import com.example.cse_412_project.entities.impl.AppUser;
import com.example.cse_412_project.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

@SpringBootApplication
public class Cse412ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cse412ProjectApplication.class, args);
    }

//    @Bean
//    CommandLineRunner init(UserRepository userRepository) {
//        return args -> {
//            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
//                AppUser user = new AppUser(name, name.toLowerCase() + "@domain.com");
//                userRepository.save(user);
//            });
//            userRepository.findAll().forEach(System.out::println);
//        };
//    }

}
