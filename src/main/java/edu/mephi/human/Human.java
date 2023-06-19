package edu.mephi.human;

public class Human {
  private int id;
  private String name;
  private String secondName;
  private String fathersName;

  public Human(int id, String name, String secondName, String fathersName) {
    this.id = id;
    this.name = name;
    this.secondName = secondName;
    this.fathersName = fathersName;
  }
  public int getId() { return id; }
  public void setId(int id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getSecondName() { return secondName; }
  public void setSecondName(String secondName) { this.secondName = secondName; }
  public String getFathersName() { return fathersName; }
  public void setFathersName(String fathersName) {
    this.fathersName = fathersName;
  }
}
