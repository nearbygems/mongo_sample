package kz.mongo.sample.impl;

import com.mongodb.client.model.Filters;
import kz.mongo.sample.model.mongo.ClientDto;
import kz.mongo.sample.model.mongo.updates.UpdateClientFields;
import kz.mongo.sample.model.web.Client;
import kz.mongo.sample.mongo.MongoAccess;
import kz.mongo.sample.register.ClientService;
import kz.mongo.sample.util.Ids;
import kz.mongo.sample.util.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

  private final MongoAccess mongoAccess;

  @Autowired
  public ClientServiceImpl(MongoAccess mongoAccess) {
    this.mongoAccess = mongoAccess;
  }

  @Override
  public void create(Client client) {
    mongoAccess.clients().insertOne(client.toDto());
  }

  @Override
  public List<Client> readAll() {
    return MongoUtil.asStream(mongoAccess.clients().find(Filters.eq(ClientDto.Fields.isRemoved, false)))
      .map(ClientDto::toClient)
      .collect(Collectors.toList());
  }

  @Override
  public Client read(String id) {
    return MongoUtil.one(mongoAccess.clients().find(Filters.eq("_id", Ids.toObjectId(id))))
      .orElseThrow(() -> new RuntimeException("fzrllvqsyd :: No client with id = `" + id + "`"))
      .toClient();
  }

  @Override
  public boolean update(Client client, String id) {
    return UpdateClientFields.of(id)
      .name(client.name)
      .email(client.email)
      .phone(client.phone)
      .apply(mongoAccess);
  }

  @Override
  public boolean delete(String id) {
    return UpdateClientFields.of(id)
      .isRemoved(true)
      .apply(mongoAccess);
  }

}
