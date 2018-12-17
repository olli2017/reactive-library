package org.aydus.reactive.reactivelibrary.post.repository;

import org.aydus.reactive.reactivelibrary.post.model.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PostRepository extends ReactiveMongoRepository<Post, String> {

}
