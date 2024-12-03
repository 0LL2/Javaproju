package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KayttajaController {

    @Autowired
    private KayttajaRepository kayttajaRepository;

    @GetMapping("/users")
    public List<Kayttaja> getUsers() {
        kayttajaRepository.save(new Kayttaja("Keijo Käyttäjä", "suola"));
        return kayttajaRepository.findAll();
    }
}
