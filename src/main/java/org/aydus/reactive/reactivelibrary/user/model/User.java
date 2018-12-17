package org.aydus.reactive.reactivelibrary.user.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

  @Id
  private String id;
  private String username;

  private String email;
  private String password;

  @Builder.Default()
  private boolean active = true;

  @Builder.Default()
  private List<String> roles = new ArrayList<>();
}
