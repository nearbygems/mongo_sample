package kz.mongo.sample.mongo;

import com.mongodb.client.MongoCollection;
import kz.mongo.sample.model.mongo.ClientDto;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoAccess implements InitializingBean {

  private final MongoConnection mongoConnection;

  @Autowired
  public MongoAccess(MongoConnection mongoConnection) {
    this.mongoConnection = mongoConnection;
  }

  private <T> MongoCollection<T> getCollection(Class<T> aClass) {
    return mongoConnection.database().getCollection(aClass.getSimpleName(), aClass);
  }

  @Override
  public void afterPropertiesSet() {
    clients = getCollection(ClientDto.class);
  }

  private MongoCollection<ClientDto> clients;

  public MongoCollection<ClientDto> clients() { return clients; }

}
