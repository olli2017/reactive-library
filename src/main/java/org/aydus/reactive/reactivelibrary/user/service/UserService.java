package org.aydus.reactive.reactivelibrary.user.service;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.aydus.reactive.reactivelibrary.user.model.User;
import org.aydus.reactive.reactivelibrary.user.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

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

  public Mono<User> register(User user) {
    return userRepository.save(User.builder()
        .roles(Arrays.asList("ROLE_USER"))
        .username(user.getUsername())
        .password(passwordEncoder.encode(user.getPassword()))
        .email(user.getEmail())
        .build()
    );
  }
}
