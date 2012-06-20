package com.agile.spirit.application;

import java.io.Serializable;

import com.agile.spirit.domain.Country;
import com.agile.spirit.domain.Gender;
import com.agile.spirit.domain.Nationality;

public class UserSearchCriteria implements Serializable {

  private static final long serialVersionUID = -199039189999045329L;

  private String name = null;
  private Gender gender = null;
  private Nationality nationality = null;
  private Country country = null;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Nationality getNationality() {
    return nationality;
  }

  public void setNationality(Nationality nationality) {
    this.nationality = nationality;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public boolean isEmpty() {
    return name == null && gender == null && nationality == null && country == null;
  }

}
