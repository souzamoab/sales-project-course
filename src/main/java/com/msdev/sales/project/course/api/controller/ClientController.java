package com.msdev.sales.project.course.api.controller;

import com.msdev.sales.project.course.domain.entity.Client;
import com.msdev.sales.project.course.domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {

        return clientRepository.findById(id)
                .map(clientFound -> ResponseEntity.ok(clientFound))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

    }

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody @Valid Client client) {
        Client clientSaved = clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer id) {

        return clientRepository.findById(id)
                .map(clientFound -> {
                    clientRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Integer id,
                                               @RequestBody Client client) {

        return clientRepository.findById(id).map(clientFound -> {
           clientFound.setName(client.getName());
           clientRepository.save(clientFound);
           return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

    }

    @GetMapping
    public ResponseEntity<List<Client>> find(Client clientFilter) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(clientFilter, matcher);

        List<Client> clients = clientRepository.findAll(example);

        return ResponseEntity.ok(clients);
    }

}
