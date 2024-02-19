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

        //Utiliza la herramienta
        //http://jsontostring.

        String body  = """
                [{
                	"op": "replace",
                	"path": "/nombre",
                	"value": "Portugués"
                }]
                """;

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

    }

}