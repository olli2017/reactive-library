package org.aydus.reactive.reactivelibrary.post.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import lombok.RequiredArgsConstructor;
import org.aydus.reactive.reactivelibrary.post.model.Post;
import org.aydus.reactive.reactivelibrary.post.repository.PostRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(value = "/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostRepository postRepository;

  @GetMapping("")
  public Flux<Post> all() {
    return this.postRepository.findAll();
  }

  @PostMapping("")
  public Mono<Post> create(@RequestBody Post post) {
    return this.postRepository.save(post);
  }

  @GetMapping("/{id}")
  public Mono<Post> get(@PathVariable("id") String id) {
    return this.postRepository.findById(id);
  }

  @PutMapping("/{id}")
  public Mono<Post> update(@PathVariable("id") String id, @RequestBody Post post) {
    return this.postRepository.findById(id)
        .map(p -> {
          p.setTitle(post.getTitle());
          p.setContent(post.getContent());

          return p;
        })
        .flatMap(postRepository::save);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  public Mono<Void> delete(@PathVariable("id") String id) {
    return this.postRepository.deleteById(id);
  }


}
