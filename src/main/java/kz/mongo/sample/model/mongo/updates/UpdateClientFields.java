package kz.mongo.sample.model.mongo.updates;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import kz.mongo.sample.mongo.MongoAccess;
import kz.mongo.sample.util.Ids;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ToString
public class UpdateClientFields {

  public ObjectId id;

  public List<UpdateClient> updates = new ArrayList<>();

  public static UpdateClientFields of(String id) {
    var ret = new UpdateClientFields();
    ret.id = Ids.toObjectId(id);
    return ret;
  }

  public UpdateClientFields isRemoved(boolean removed) {
    updates.add(new UpdateClientIsRemoved(removed));
    return this;
  }

  public UpdateClientFields name(String name) {
    updates.add(new UpdateClientName(name));
    return this;
  }

  public UpdateClientFields email(String email) {
    updates.add(new UpdateClientEmail(email));
    return this;
  }

  public UpdateClientFields phone(String phone) {
    updates.add(new UpdateClientPhone(phone));
    return this;
  }

  public boolean apply(MongoAccess mongoAccess) {

    var bson = updates.stream()
      .flatMap(UpdateClient::toStreamBson)
      .collect(toList());

    if (bson.isEmpty()) {
      return false;
    }

    var updateResult = mongoAccess.clients().updateOne(Filters.eq("_id", id), Updates.combine(bson));

    return updateResult.getModifiedCount() > 0;
  }

}
