package kz.mongo.sample;

import kz.mongo.sample.model.Gender;
import kz.mongo.sample.model.PersonDto;
import kz.mongo.sample.mongo.MongoAccess;
import kz.mongo.sample.util.Ids;
import kz.mongo.sample.util.MongoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.mongodb.client.model.Filters.eq;

@SpringBootTest
class MongoSampleApplicationTests {

  @Autowired
  private MongoAccess mongoAccess;

  @Test
  void contextLoads() {

    var personDto = new PersonDto();
    personDto.id = Ids.generate();
    personDto.name = "Bergen";
    personDto.gender = Gender.MAN;

    //
    //
    mongoAccess.person().insertOne(personDto);
    //
    //

    var person = MongoUtil.one(mongoAccess.person().find(eq("_id", personDto.id))).orElseThrow();

    System.out.println(person);

  }

}
