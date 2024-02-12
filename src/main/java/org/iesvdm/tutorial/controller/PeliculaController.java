package org.iesvdm.tutorial.controller;


import lombok.extern.slf4j.Slf4j;
import org.iesvdm.tutorial.domain.Pelicula;
import org.iesvdm.tutorial.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/peliculas")
public class PeliculaController {
    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping({"","/"})
    public List<Pelicula> all() {
        log.info("Accediendo a todas las películas");
        return this.peliculaService.all();
    }

    @PostMapping({"","/"})
    public Pelicula newPelicula(@RequestBody Pelicula pelicula) {
        log.info("Creando una pelicula = " + pelicula);
        return this.peliculaService.save(pelicula);
    }

    @GetMapping("/{id}")
    public Pelicula one(@PathVariable("id") Long id) {
        log.info("Buscar pelicula con id = " + id);
        return this.peliculaService.one(id);
    }

    @PutMapping("/{id}")
    public Pelicula replacePelicula(@PathVariable("id") Long id, @RequestBody Pelicula pelicula) {
        log.info("Actualizar pelicula con id = " + id + "\n pelicula = " + pelicula);
        return this.peliculaService.replace(id, pelicula);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable("id") Long id) {
        this.peliculaService.delete(id);
    }


}
