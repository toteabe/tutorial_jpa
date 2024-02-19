package org.iesvdm.tutorial.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.tutorial.domain.Idioma;
import org.iesvdm.tutorial.domain.Pelicula;
import org.iesvdm.tutorial.exception.EntityNotFoundException;
import org.iesvdm.tutorial.repository.IdiomaRepository;
import org.iesvdm.tutorial.service.IdiomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/api/idiomas")
public class IdiomaController {

    @Autowired
    private IdiomaService idiomaService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping({"", "/"})
    public List<Idioma> all() {
        return idiomaService.all();
    }

    @GetMapping( "/{id}")
    public Idioma one(@PathVariable("id") Long id) {
        return idiomaService.one(id);
    }

    @PostMapping({"","/"})
    public Idioma newIdioma(@RequestBody Idioma idioma) {
        log.info("Creando una idioma = " + idioma);
        return this.idiomaService.save(idioma);
    }


    //https://www.baeldung.com/spring-rest-json-patch
    //https://jsonpatch.com/
    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Idioma> updateIdioma(@PathVariable Long id, @RequestBody JsonPatch patch) {
        try {
            Idioma idioma = idiomaService.one(id);
            Idioma idiomaPatched = applyPatchToIdioma(patch, idioma);
            idiomaService.save(idiomaPatched);

            return ResponseEntity.ok(idiomaPatched);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    private Idioma applyPatchToIdioma(JsonPatch patch, Idioma targetIdioma) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetIdioma, JsonNode.class));

        //PARA EVITAR  Already had POJO for id
        ArrayNode arrayNodePeliculas = (ArrayNode) patched.get("peliculas");
        for (JsonNode pelicula : arrayNodePeliculas) {
            ObjectNode peliculaON = (ObjectNode) pelicula;
            //REEMPLAZO EL IDIOMA POR OBJETO NUMBER DE ID PARA EVITAR Already had POJO for id Exception
            //se da la Exception incluso con el parametro scope en JsonIdentityInfo
            peliculaON.set("idioma", pelicula.get("idioma").get("id"));
        }

//        List<Integer> posicionPeliculaConIdiomaACambiar = new ArrayList<>();
//        JsonNode patchedJsonNodeIdiomaEnPeliculas = null;
//        int cont = 0;
//        for (JsonNode node : patched.get("peliculas")) {
//            JsonNode jsonNodeIdiomaEnPeliculas = node.findValue("idioma");
//            long idAux  = jsonNodeIdiomaEnPeliculas.findValue("id").longValue();
//            if (targetIdioma.getId() == idAux) {
//                posicionPeliculaConIdiomaACambiar.add(cont);
//                if (patchedJsonNodeIdiomaEnPeliculas == null)
//                    patchedJsonNodeIdiomaEnPeliculas = patch.apply(jsonNodeIdiomaEnPeliculas);
//            }
//            cont++;
//        }
//        if (patchedJsonNodeIdiomaEnPeliculas != null) {
//            ArrayNode arrayNodePeliculas = (ArrayNode) patched.get("peliculas");
//            for (Integer i : posicionPeliculaConIdiomaACambiar) {
//                ObjectNode pelicula = (ObjectNode) arrayNodePeliculas.get(i);
//                pelicula.replace("idioma", patchedJsonNodeIdiomaEnPeliculas);
//            }
//        }

        return objectMapper.treeToValue(patched, Idioma.class);
    }

}
