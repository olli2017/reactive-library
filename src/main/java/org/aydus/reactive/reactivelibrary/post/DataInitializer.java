package org.aydus.reactive.reactivelibrary.post;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aydus.reactive.reactivelibrary.post.model.Post;
import org.aydus.reactive.reactivelibrary.post.repository.PostRepository;
import org.aydus.reactive.reactivelibrary.user.model.User;
import org.aydus.reactive.reactivelibrary.user.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    log.info("start data initialization  ...");
    postRepository
        .deleteAll()
        .thenMany(Flux.just("Post one", "Post two")
            .flatMap(title -> postRepository
                .save(Post.builder().title(title).content("content of " + title).build())
            )
        )
        .log()
        .subscribe(null, null, () -> log.info("done initialization..."));

    log.info("start users initialization  ...");
    userRepository
        .deleteAll()
        .thenMany(Flux.just("user", "admin")
            .flatMap(username -> {
                  List<String> roles = "user".equals(username)
                      ? Arrays.asList("ROLE_USER")
                      : Arrays.asList("ROLE_USER", "ROLE_ADMIN");

                  User user = User.builder()
                      .roles(roles)
                      .username(username)
                      .password(passwordEncoder.encode("password"))
                      .email(username + "@example.com")
                      .build();
                  return userRepository.save(user);
                }
            )
        )
        .log()
        .subscribe(null, null, () -> log.info("done users initialization..."));
  }
}
