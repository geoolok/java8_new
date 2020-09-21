package model;

import java.util.Objects;

public class Person {
    private int age;
    private String name;
    private Status status;

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    public Person(int age, String name, Status status) {
        this.age = age;
        this.name = name;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age &&
                name.equals(person.name) &&
                status == person.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, status);
    }

    public Person() {
        this.age = 18;
        this.name = "雷梅梅";
    }

    public Person(int age) {
        this.age = age;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public enum Status{
        BUSY,
        FREE,
        VOCATION;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
