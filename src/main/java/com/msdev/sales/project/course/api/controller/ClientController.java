package com.msdev.sales.project.course.api.controller;

import com.msdev.sales.project.course.domain.entity.Client;
import com.msdev.sales.project.course.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer id) {

        Optional<Client> client = clientRepository.findById(id);

        if(client.isPresent()) {
            clientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }

}
