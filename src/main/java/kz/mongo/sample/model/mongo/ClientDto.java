package kz.mongo.sample.model.mongo;

import kz.mongo.sample.model.web.Client;
import kz.mongo.sample.util.Ids;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.bson.types.ObjectId;

@ToString
@FieldNameConstants
public class ClientDto {

  public ObjectId id;
  public String name;
  public String email;
  public String phone;
  public boolean isRemoved;

  public Client toClient() {
    var ret = new Client();
    ret.id = Ids.toStrId(id);
    ret.name = name;
    ret.email = email;
    ret.phone = phone;
    return ret;
  }

  public String strId() { return Ids.toStrId(id); }

}
