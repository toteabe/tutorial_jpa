package org.iesvdm.tutorial;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest
public class WebTestClientTests {

    private WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();

    @Test
    public void patchOpReplaceTest() {

        //Puedo utilizar la herramienta
        //http://jsontostring

        //O directamente triple quote de Java moderno
        String body  = """
                [{
                	"op": "replace",
                	"path": "/nombre",
                	"value": "Portugués"
                }]
                """;

        //PARCHEO Idioma 1 a Portugués
        webTestClient.patch()
                .uri("/v1/api/idiomas/1")
                .headers(httpHeaders -> httpHeaders.add("Content-Type", "application/json-patch+json"))
                .accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(body))
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                //TESTEO CON JSONPATH JSONPATH CHEAT SHEET: https://zappysys.com/blog/jsonpath-examples-expression-cheetsheet/
                .jsonPath("$").isNotEmpty()
                //.jsonPath("$.nombre").isEqualTo("Italiano");
                .jsonPath("$.nombre").isEqualTo("Portugués");


        //Obtengo idioma 1 de la coleccion
        webTestClient.get()
                .uri("/v1/api/idiomas/1")
                .headers(httpHeaders -> httpHeaders.add("Content-Type", "application/json-patch+json"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                //TESTEO CON JSONPATH JSONPATH CHEAT SHEET: https://zappysys.com/blog/jsonpath-examples-expression-cheetsheet/
                .jsonPath("$").isNotEmpty()
                //.jsonPath("$.nombre").isEqualTo("Italiano");
                .jsonPath("$.nombre").isEqualTo("Portugués");

    }

}
