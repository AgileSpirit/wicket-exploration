package com.agile.spirit.domain;

import java.io.Serializable;

public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String firstName;
  private String lastName;
  private Gender gender;
  private Nationality nationality;
  private Country country;
  private String area;
  private String passport;
  private String email;
  private String password;

  public static User create() {
    return create("", "", Gender.MALE, Nationality.FRENCH, "", Country.FRANCE, "", "", "");
  }

  public static User create(String firstName, String lastName, Gender gender, Nationality nationality, String passport, Country country, String area, String email, String password) {
    User user = new User();
    user.id = 0;
    user.firstName = firstName;
    user.lastName = lastName;
    user.gender = gender;
    user.nationality = nationality;
    user.passport = passport;
    user.country = country;
    user.area = area;
    user.email = email;
    user.password = password;
    return user;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

  public String getPassport() {
    return passport;
  }

  public void setPassport(String passport) {
    this.passport = passport;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


}
