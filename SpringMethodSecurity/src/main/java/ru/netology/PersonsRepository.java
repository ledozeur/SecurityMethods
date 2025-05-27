package ru.netology;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonsRepository extends JpaRepository<Person, Key> {
    @Query("SELECT p FROM Person p WHERE p.city = :city")
    List<Person> findByCity(@Param("city") String city);

    @Query("SELECT p FROM Person p WHERE p.key.age < :age ORDER BY p.key.age ASC")
    List<Person> findByAge(@Param("age") int age);

    @Query("SELECT p FROM Person p WHERE p.key.name = :name AND p.key.surname = :surname")
    List<Person> findAllByKeyNameAndKeySurname(@Param("name") String name, @Param("surname") String surname);


}


