package kz.mongo.sample.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Component
public class MongoConnection implements InitializingBean {

  private MongoDatabase database;

  private MongoClient mongoClient;

  public MongoClient mongoClient() {
    return mongoClient;
  }

  public MongoDatabase database() {
    return database;
  }

  @Override
  public void afterPropertiesSet() {
    var pojoBuilder = PojoCodecProvider.builder();
    pojoBuilder.automatic(true);

    var pojoCodecProvider = pojoBuilder.build();

    var codecRegistry = MongoClient.getDefaultCodecRegistry();

    var finishedCodecRegistry = fromRegistries(codecRegistry, fromProviders(pojoCodecProvider));

    var mongoClientOptions = MongoClientOptions
      .builder()
      .codecRegistry(finishedCodecRegistry)
      .build();

    mongoClient = new MongoClient("localhost:27017", mongoClientOptions);
    database = mongoClient.getDatabase("sample");
  }
}
