package com.example.models;

public class Person {

  private String firstName = "";

  private String lastName = "";

  private String job;

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

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public String toString() {
    return firstName + " " + lastName;
  }
}
