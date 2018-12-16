package org.aydus.reactive.reactivelibrary.user.controller;

import lombok.RequiredArgsConstructor;
import org.aydus.reactive.reactivelibrary.user.model.User;
import org.aydus.reactive.reactivelibrary.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/users")
  public Flux<User> getAllUsers() {
    return userService.getAll();
  }

  @PostMapping("/users")
  public Mono<User> registerUser(@RequestBody User user) {
    return userService.save(user);
  }

  @GetMapping("users/deleteAll")
  public Mono<Void> deleteAll() {
    return userService.deleteAll();
  }
}
