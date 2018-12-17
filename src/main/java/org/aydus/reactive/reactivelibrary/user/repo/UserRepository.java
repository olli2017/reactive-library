package org.aydus.reactive.reactivelibrary.user.repo;

import org.aydus.reactive.reactivelibrary.user.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

   Mono<User> findByUsername(String userName);
}
