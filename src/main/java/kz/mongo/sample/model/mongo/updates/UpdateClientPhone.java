package kz.mongo.sample.model.mongo.updates;

import kz.mongo.sample.model.mongo.ClientDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.conversions.Bson;

import java.util.stream.Stream;

import static com.mongodb.client.model.Updates.set;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClientPhone implements UpdateClient {

  public String phone;

  @Override
  public Stream<Bson> toStreamBson() { return Stream.of(set(ClientDto.Fields.phone, phone)); }

}