package com.lairon.laberis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {

    @Id
    @NonNull
    private String login;
    @NonNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @NonNull
    private String token;
    @NonNull
    private UserRole role;

}
