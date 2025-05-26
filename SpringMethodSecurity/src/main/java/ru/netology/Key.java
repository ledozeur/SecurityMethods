package ru.netology;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class Key implements Serializable {
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private int age;

    public Key() {
    }

    @Override
    public String toString() {
        return "Name: " + name + " Surname: " + surname + " Age: " + age;
    }

}
