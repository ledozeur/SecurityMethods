package ru.netology;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Controller {

    private final PersonsRepository personsRepository;


    public Controller(PersonsRepository personsRepository, Key key) {
        this.personsRepository = personsRepository;

    }

    @GetMapping("all-roles")
    public String getAllRoles() {
        return "ALL ROLES";
    }

    @GetMapping("/persons/by-city")
    @Secured({"READ"})
    public String getPersonsByCity(@RequestParam("city") String city) {
        return personsRepository.findByCity(city).toString() + " READ";
    }

    @Secured({"WRITE"})
    @GetMapping("/persons/by-name-surname")
    public String getPersonsBbyNameSurname(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        return personsRepository.findAllByKeyNameAndKeySurname(name, surname).toString() + " READ";
    }

    @GetMapping("/persons/by-age")
    @PreAuthorize("hasRole('DELETE')")
    public String getPersonsByAge(@RequestParam("age") int age) {
        return personsRepository.findByAge(age).toString() + " DELETE";
    }

    @GetMapping("/persons/by-username")
    @PreAuthorize("authentication.principal.username == #username")
    public String getPersonsByUsername(@RequestParam("username") String username) {
        return username;
    }

}
