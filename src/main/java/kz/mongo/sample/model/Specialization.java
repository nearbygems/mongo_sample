package kz.mongo.sample.model;

import org.bson.types.ObjectId;

import java.util.List;

public class Specialization {

  public ObjectId id;

  public String name;
  public String shortDescription;

  public double rating;
  public Organization offeredBy;

  public List<Instructor> instructors;


}
