package org.aydus.reactive.reactivelibrary.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aydus.reactive.reactivelibrary.post.model.Post;
import org.aydus.reactive.reactivelibrary.post.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

  private final PostRepository postRepository;

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
  }
}
