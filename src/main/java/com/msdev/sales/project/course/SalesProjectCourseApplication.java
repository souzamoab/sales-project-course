package com.msdev.sales.project.course;

import com.msdev.sales.project.course.domain.entity.Client;
import com.msdev.sales.project.course.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SalesProjectCourseApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientRepository clientRepository) {
        return args -> {
            clientRepository.save(new Client("Moab"));
            clientRepository.save(new Client("Joseph"));

            List<Client> clients = clientRepository.getAll();
            clients.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesProjectCourseApplication.class, args);
    }

}
