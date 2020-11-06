package kz.mongo.sample.model.web;

import kz.mongo.sample.model.mongo.ClientDto;
import kz.mongo.sample.util.Ids;
import lombok.ToString;
import org.bson.types.ObjectId;

@ToString
public class Client {

  public String id;
  public String name;
  public String email;
  public String phone;

  public ClientDto toDto() {
    var ret = new ClientDto();
    ret.id = objectId();
    ret.name = name;
    ret.email = email;
    ret.phone = phone;
    return ret;
  }

  public ObjectId objectId() {
    return Ids.toObjectId(id);
  }

}
