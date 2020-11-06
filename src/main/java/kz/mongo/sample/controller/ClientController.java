package kz.mongo.sample.controller;

import kz.mongo.sample.model.web.Client;
import kz.mongo.sample.register.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

  private final ClientService clientService;

  @Autowired
  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @PostMapping("/clients")
  public ResponseEntity<?> create(@RequestBody Client client) {
    clientService.create(client);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/clients")
  public ResponseEntity<List<Client>> read() {

    final var clients = clientService.readAll();

    return clients != null && !clients.isEmpty()
      ? new ResponseEntity<>(clients, HttpStatus.OK)
      : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/clients/{id}")
  public ResponseEntity<Client> read(@PathVariable("id") String id) {

    final var client = clientService.read(id);

    return clientService.read(id) != null
      ? new ResponseEntity<>(client, HttpStatus.OK)
      : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping("/clients/{id}")
  public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Client client) {
    return clientService.update(client, id)
      ? new ResponseEntity<>(HttpStatus.OK)
      : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }

  @DeleteMapping("/clients/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") String id) {
    return clientService.delete(id)
      ? new ResponseEntity<>(HttpStatus.OK)
      : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
  }

}
