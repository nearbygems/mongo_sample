package kz.mongo.sample;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import kz.greetgo.util.RND;
import kz.mongo.sample.model.mongo.ClientDto;
import kz.mongo.sample.model.web.Client;
import kz.mongo.sample.mongo.MongoAccess;
import kz.mongo.sample.util.Ids;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MongoSampleApplicationTests {

  @Autowired
  protected MongoAccess mongoAccess;

  protected Client rndClient() {
    var ret = new Client();
    ret.id = Ids.generateStr();
    ret.name = RND.str(7);
    ret.email = RND.str(8);
    ret.phone = RND.str(9);
    return ret;
  }

  protected ClientDto rndClientDto() {
    var ret = new ClientDto();
    ret.id = Ids.generate();
    ret.name = RND.str(7);
    ret.email = RND.str(8);
    ret.phone = RND.str(9);
    return ret;
  }

  protected void ins(ClientDto... clients) {
    for (var client : clients) {
      mongoAccess.clients().insertOne(client);
    }
  }

  @AfterEach
  protected void clean() {
    mongoAccess.clients().updateMany(
      Filters.eq(ClientDto.Fields.isRemoved, false),
      Updates.set(ClientDto.Fields.isRemoved, true));
  }

}
