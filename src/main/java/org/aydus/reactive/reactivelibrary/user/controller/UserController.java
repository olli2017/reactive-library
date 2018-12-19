package org.aydus.reactive.reactivelibrary.user.controller;

import lombok.RequiredArgsConstructor;
import org.aydus.reactive.reactivelibrary.user.model.User;
import org.aydus.reactive.reactivelibrary.user.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @PostMapping("/users/register")
  public Mono<User> register(@RequestBody User user) {
    return userService.register(user);
  }

  @GetMapping("/users/{username}")
  public Mono<User> get(@PathVariable() String username) {
    return userService.findByUsername(username);
  }

  @DeleteMapping("users/deleteAll")
  public Mono<Void> deleteAll() {
    return userService.deleteAll();
  }
}
