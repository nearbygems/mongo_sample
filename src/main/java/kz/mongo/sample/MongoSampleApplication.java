package kz.mongo.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MongoSampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(MongoSampleApplication.class, args);
  }

}
