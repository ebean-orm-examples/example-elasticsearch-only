package org.example.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

  String line1;
  String line2;
  String city;
  String country;

  public Address(String line1, String city) {
    this.line1 = line1;
    this.city = city;
  }

  public String getLine1() {
    return line1;
  }

  public void setLine1(String line1) {
    this.line1 = line1;
  }

  public String getLine2() {
    return line2;
  }

  public void setLine2(String line2) {
    this.line2 = line2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
