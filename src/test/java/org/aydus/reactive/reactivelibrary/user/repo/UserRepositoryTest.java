package org.aydus.reactive.reactivelibrary.user.repo;

import static org.junit.Assert.*;

import org.aydus.reactive.reactivelibrary.ReactiveLibraryApplication;
import org.aydus.reactive.reactivelibrary.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Test
  public void saveTest() {
    User testUser = new User(null, "email", "name", "password");
    userRepository.save(testUser).block();
  }

  @Test
  public void getAllTest() {
    userRepository.findAll().subscribe(System.out::println);
  }

}