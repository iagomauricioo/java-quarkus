package br.com.alura.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Person extends PanacheEntity {
    public String name;
    public int age;

    public static Person findByName(String name) {
        return find("name", name).firstResult();
    }

    public static List<Person> findAdults() {
        return list("age >= ?1", 18);
    }
}
