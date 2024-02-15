package org.iesvdm.tutorial.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.iesvdm.tutorial.domain.Idioma;
import org.iesvdm.tutorial.exception.EntityNotFoundException;
import org.iesvdm.tutorial.repository.IdiomaRepository;
import org.iesvdm.tutorial.service.IdiomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //https://www.baeldung.com/spring-rest-json-patch
    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Idioma> updateCustomer(@PathVariable Long id, @RequestBody JsonPatch patch) {
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
        return objectMapper.treeToValue(patched, Idioma.class);
    }

}
