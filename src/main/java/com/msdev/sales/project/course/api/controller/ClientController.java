package com.msdev.sales.project.course.api.controller;

import com.msdev.sales.project.course.domain.entity.Client;
import com.msdev.sales.project.course.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {

        Optional<Client> client = clientRepository.findById(id);

        if(client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        Client clientSaved = clientRepository.save(client);
        return ResponseEntity.status(201).body(clientSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer id) {

        Optional<Client> client = clientRepository.findById(id);

        if(client.isPresent()) {
            clientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateClient(@PathVariable Integer id,
                                               @RequestBody Client client) {

        return clientRepository.findById(id).map(clientToUpdate -> {
           clientToUpdate.setName(client.getName());
           clientRepository.save(clientToUpdate);
           return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping
    public ResponseEntity<List<Client>> find(Client clientFilter) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(clientFilter, matcher);

        List<Client> clients = clientRepository.findAll(example);

        return ResponseEntity.ok(clients);
    }

}
