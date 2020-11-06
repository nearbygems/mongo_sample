package kz.mongo.sample.register;

import kz.mongo.sample.model.web.Client;

import java.util.List;

public interface ClientService {

  void create(Client client);

  List<Client> readAll();

  Client read(String id);

  boolean update(Client client, String id);

  boolean delete(String id);

}
