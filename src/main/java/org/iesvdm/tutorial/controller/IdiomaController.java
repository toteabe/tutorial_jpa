package org.iesvdm.tutorial.controller;

import org.iesvdm.tutorial.domain.Idioma;
import org.iesvdm.tutorial.repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/idiomas")
public class IdiomaController {

    @Autowired
    private IdiomaRepository idiomaRepository;

    @GetMapping({"", "/"})
    public List<Idioma> all() {
        return idiomaRepository.findAll();
    }

}
