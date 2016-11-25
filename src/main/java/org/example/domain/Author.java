package org.example.domain;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.DocEmbedded;
import com.avaje.ebean.annotation.DocStore;

import javax.persistence.Id;
import java.util.UUID;

@DocStore
public class Author extends Model {

  @Id
  UUID id;

  String name;

  @DocEmbedded
  Address address;

  public Author(String name) {
    this.name = name;
  }


  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
