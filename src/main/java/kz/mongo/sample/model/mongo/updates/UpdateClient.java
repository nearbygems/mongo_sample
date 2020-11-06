package kz.mongo.sample.model.mongo.updates;

import org.bson.conversions.Bson;

import java.util.stream.Stream;

public interface UpdateClient {

  Stream<Bson> toStreamBson();

}
