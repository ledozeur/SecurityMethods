package ru.netology;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person", schema = "netology")
public class Person {
    @Id
    @EmbeddedId
    private Key key;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "city_of_living")
    private String city;

    @Override
    public String toString() {
        return "Person{" + "key=" + key + ", phoneNumber='" + phoneNumber + '\'' + ", city='" + city + '\'';
    }

}
