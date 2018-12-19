package org.aydus.reactive.reactivelibrary.user.service;

import lombok.RequiredArgsConstructor;
import org.aydus.reactive.reactivelibrary.user.model.User;
import org.aydus.reactive.reactivelibrary.user.repo.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public Mono<User> save(User user) {
    return userRepository.save(user);
  }

  public Flux<User> getAll() {
    return userRepository.findAll();
  }

  public Mono<Void> deleteAll() {
    return userRepository.deleteAll();
  }

  public Mono<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
