package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  private static int count;
  public Person() {
    this("", 0, 0.0d);
    count++;
  }

  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
  }

  public void setSSN(String value) {
    String old = "";
    if(this.ssn != null) {
      old = this.ssn;
    }
    this.ssn = value;
    this.pcs.firePropertyChange("ssn", (Object) old, (Object) value);
    this.propertyChangeFired = true;
  }


  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }

  public String becomeJudge() {
    return "The Honorable " + name;
  }

  public int timeWarp() {
    return age + 10;
  }

  @Override
  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age + " salary:" + this.salary + "]";
  }

  // Returns age of person
  public int getAge() {
    return this.age;
  }

  // Sets age of person to given value
  // given age must be greater than or equal to 0
  public void setAge(int a) {
    if(a < 0) {
      throw new IllegalArgumentException();
    }
    this.age = a;
  }

  // Returns age of person
  public String getName() {
    return this.name;
  }

  // Sets the name of the person to given value
  // given string must not be null
  public void setName(String n) {
    if(n == null) {
      throw new IllegalArgumentException();
    }
    this.name = n;
  }

  // Returns person's salary
  public double getSalary() {
    return this.salary;
  }

  // Sets this person's salary
  public void setSalary(Double s) {
    this.salary = s;
  }

  // Returns this person's salary
  public String getSSN() {
    return this.ssn;
  }

  // Returns the total times a person has been created
  public static int count() {
    return count;
  }

  @Override
  public boolean equals(Object o) {
    if(o == null) {
      return false;
    }
    if(getClass() != o.getClass()) {
      return false;
    }
    Person p = (Person) o;
    return (this.getName().equals(p.getName()) && this.getAge() == p.getAge());
  }

  public int compareTo(Person o) {
    return (int) (o.getSalary() - this.getSalary());
  }

  public static List<Person> getNewardFamily() {
    List<Person> family = new ArrayList<Person>();
    family.add(new Person("Ted", 41, 250000.0));
    family.add(new Person("Charlotte", 43, 150000.0));
    family.add(new Person("Michael", 22, 10000.0));
    family.add(new Person("Matthew", 15, 0.0));
    return family;
  }

  static class AgeComparator implements Comparator<Person> {
    public int compare(Person p, Person o) {

      return p.getAge() - o.getAge();
    }
  }






  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
