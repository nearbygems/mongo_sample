package kz.mongo.sample.tests;

import com.mongodb.client.model.Filters;
import kz.greetgo.util.RND;
import kz.mongo.sample.MongoSampleApplicationTests;
import kz.mongo.sample.register.ClientService;
import kz.mongo.sample.util.MongoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientServiceImplTest extends MongoSampleApplicationTests {

  @Autowired
  private ClientService clientService;

  @Test
  void create() {

    var create = rndClient();

    //
    //
    clientService.create(create);
    //
    //

    var client = MongoUtil.one(mongoAccess.clients().find(Filters.eq("_id", create.objectId()))).orElse(null);

    assertThat(client).isNotNull();
    assertThat(client.name).isEqualTo(create.name);
    assertThat(client.email).isEqualTo(create.email);
    assertThat(client.phone).isEqualTo(create.phone);
    assertThat(client.isRemoved).isFalse();

  }

  @Test
  void readAll() {

    var client1 = rndClientDto();
    var client2 = rndClientDto();
    var client3 = rndClientDto();
    client3.isRemoved = true;

    ins(client1, client2, client3);

    //
    //
    var clients = clientService.readAll();
    //
    //

    assertThat(clients).isNotNull();
    assertThat(clients).hasSize(2);

    var map = clients.stream().collect(Collectors.toMap(x -> x.id, x -> x));

    {
      assertThat(map).containsKey(client1.strId());

      var client = map.get(client1.strId());

      assertThat(client).isNotNull();
      assertThat(client.name).isEqualTo(client1.name);
      assertThat(client.email).isEqualTo(client1.email);
      assertThat(client.phone).isEqualTo(client1.phone);
    }

    {
      assertThat(map).containsKey(client2.strId());

      var client = map.get(client2.strId());

      assertThat(client).isNotNull();
      assertThat(client.name).isEqualTo(client2.name);
      assertThat(client.email).isEqualTo(client2.email);
      assertThat(client.phone).isEqualTo(client2.phone);
    }

    assertThat(map).doesNotContainKeys(client3.strId());

  }

  @Test
  void read() {

    var create = rndClientDto();

    ins(create);

    //
    //
    var client = clientService.read(create.strId());
    //
    //

    assertThat(client).isNotNull();
    assertThat(client.name).isEqualTo(create.name);
    assertThat(client.email).isEqualTo(create.email);
    assertThat(client.phone).isEqualTo(create.phone);

  }

  @Test
  void update() {

    var create = rndClient();

    ins(create.toDto());

    var newName = RND.str(7);
    var newEmail = RND.str(7);
    var newPhone = RND.str(7);

    create.name = newName;
    create.email = newEmail;
    create.phone = newPhone;

    //
    //
    var update = clientService.update(create, create.id);
    //
    //

    assertThat(update).isTrue();

    var client = MongoUtil.one(mongoAccess.clients().find(Filters.eq("_id", create.objectId()))).orElse(null);

    assertThat(client).isNotNull();
    assertThat(client.name).isEqualTo(create.name);
    assertThat(client.email).isEqualTo(create.email);
    assertThat(client.phone).isEqualTo(create.phone);
    assertThat(client.isRemoved).isFalse();

  }

  @Test
  void delete() {

    var create = rndClientDto();

    ins(create);

    //
    //
    var delete = clientService.delete(create.strId());
    //
    //

    assertThat(delete).isTrue();

    var client = MongoUtil.one(mongoAccess.clients().find(Filters.eq("_id", create.id))).orElse(null);

    assertThat(client).isNotNull();
    assertThat(client.isRemoved).isTrue();

  }

}
