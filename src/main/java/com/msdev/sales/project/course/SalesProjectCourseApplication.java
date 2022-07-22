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
            System.out.println("Saving");
            clientRepository.save(new Client("Moab"));
            clientRepository.save(new Client("Joseph"));

            List<Client> clients = clientRepository.findAll();
            clients.forEach(System.out::println);

            System.out.println("Updating");
            clients.forEach(client -> {
                client.setName(client.getName() + " updated");
                clientRepository.save(client);
            });

            clients = clientRepository.findAll();
            clients.forEach(System.out::println);

            System.out.println("Searching by name");
            clientRepository.findByNameLike("Joseph").forEach(System.out::println);

            System.out.println("Deleting");
            clientRepository.findAll().forEach(client -> {
                clientRepository.delete(client);
            });

            clients = clientRepository.findAll();

            if(clients.isEmpty()) {
                System.out.println("Empty");
            }

            clients.forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesProjectCourseApplication.class, args);
    }

}
