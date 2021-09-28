package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;
@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student rowan = new Student(
                    "Rowan",
                    "bettjemanrowan@gmail.com",
                    LocalDate.of(2001, DECEMBER, 3)

            );
            Student roman = new Student(
                    "Roman",
                    "chmeloroman@gmail.com",
                    LocalDate.of(2002, DECEMBER, 3)

            );

            repository.saveAll(
                    List.of(rowan, roman)
            );
        };
    }
}
