package kz.mongo.sample.util;

import com.mongodb.client.MongoIterable;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MongoUtil {

  public static <T> Optional<T> one(MongoIterable<T> iterable) {
    try (var iterator = iterable.iterator()) {
      if (iterator.hasNext()) {
        var ret = Optional.of(iterator.next());
        if (iterator.hasNext()) {
          throw new RuntimeException();
        }
        return ret;
      } else {
        return Optional.empty();
      }
    }
  }

  public static <T> Stream<T> asStream(MongoIterable<T> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false);
  }

}
