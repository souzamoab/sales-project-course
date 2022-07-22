package com.msdev.sales.project.course;

import com.msdev.sales.project.course.domain.entity.Client;
import com.msdev.sales.project.course.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalesProjectCourseApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientRepository clientRepository) {
        return args -> {
            System.out.println("Saving");
            clientRepository.save(new Client("Moab"));
            clientRepository.save(new Client("Joseph"));

            boolean exists = clientRepository.existsByName("Moab");
            System.out.println("Have a client with name Moab? " + exists);

            clientRepository.deleteByName("Moab");

            exists = clientRepository.existsByName("Moab");
            System.out.println("Have a client with name Moab? " + exists + ", because he's deleted!");

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesProjectCourseApplication.class, args);
    }

}
