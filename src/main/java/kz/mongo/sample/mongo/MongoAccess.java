package kz.mongo.sample.mongo;

import com.mongodb.client.MongoCollection;
import kz.mongo.sample.model.PersonDto;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoAccess implements InitializingBean {

  @Autowired
  private MongoConnection mongoConnection;

  private MongoCollection<PersonDto> person;

  @Override
  public void afterPropertiesSet() {
    person = getCollection(PersonDto.class);
  }

  private <T> MongoCollection<T> getCollection(Class<T> aClass) {
    return mongoConnection.database().getCollection(aClass.getSimpleName(), aClass);
  }

  public MongoCollection<PersonDto> person() {
    return person;
  }
}
