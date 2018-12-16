package org.aydus.reactive.reactivelibrary.user.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.aydus.reactive.reactivelibrary.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Test
  public void saveTest() {
    User testUser = new User(null, "email", "name", "password");
    User savedUser = userRepository.save(testUser).block();

    assertEquals("name", savedUser.getName());
    assertEquals("email", savedUser.getEmail());
    assertEquals("password", savedUser.getPassword());
  }

  @Test
  public void getAllTest() {
    User testUser = new User(null, "email", "name", "password");
    userRepository.save(testUser).block();

    assertTrue(userRepository.findAll().collectList().block().size() > 0);
  }

  @Test
  public void deleteAllTest() {
    User testUser1 = new User(null, "email", "name", "password");
    User testUser2 = new User(null, "email", "name", "password");
    userRepository.save(testUser1).block();
    userRepository.save(testUser2).block();

    assertEquals(2, userRepository.findAll().count().block().longValue());

    userRepository.deleteAll().block();

    assertEquals(0, userRepository.findAll().count().block().longValue());
  }

}