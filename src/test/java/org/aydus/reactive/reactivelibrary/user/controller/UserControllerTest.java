package org.aydus.reactive.reactivelibrary.user.controller;

import org.aydus.reactive.reactivelibrary.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserControllerTest {

  @Autowired
  WebTestClient webTestClient;

  @Test
  public void getAllUsers() {
    webTestClient.get().uri("/users")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBodyList(User.class);
  }

  @Test
  public void registerUser() {
    User testUser = new User(null, "email", "name", "password");

    webTestClient.post().uri("/users")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .body(Mono.just(testUser), User.class)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody()
        .jsonPath("$.id").isNotEmpty()
        .jsonPath("$.email").isEqualTo("email")
        .jsonPath("$.name").isEqualTo("name");
  }

  @Test
  public void deleteAll() {
    webTestClient.delete().uri("/users/deleteAll")
        .exchange()
        .expectStatus().isOk();
  }
}