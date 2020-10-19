package kz.mongo.sample.util;

import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;

public class Ids {

  private final static ThreadLocal<SecureRandom> rnd = ThreadLocal.withInitial(SecureRandom::new);

  public static @NotNull ObjectId generate() {
    byte[] bytes = new byte[12];
    rnd.get().nextBytes(bytes);
    return new ObjectId(bytes);
  }
}
