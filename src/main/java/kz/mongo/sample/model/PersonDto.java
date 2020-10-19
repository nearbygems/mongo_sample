package kz.mongo.sample.model;

import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.bson.types.ObjectId;

@ToString
@FieldNameConstants
public class PersonDto {
  public ObjectId id;
  public String name;
  public Gender gender;
}
