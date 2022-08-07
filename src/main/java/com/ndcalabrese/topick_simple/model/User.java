package com.ndcalabrese.topick_simple.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @Email
    @NotEmpty(message = "Email is required")
    private String email;

    private Instant created;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
    name = "users_roles",
    joinColumns = @JoinColumn(
            name = "user_id", referencedColumnName = "userId"),
    inverseJoinColumns = @JoinColumn(
            name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public User(String username,
                String email,
                String password,
                Collection<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.created = Instant.now();
        this.roles = roles;
    }

    public String ConvertDate () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
                .withZone(ZoneId.systemDefault());

        return formatter.format(this.created);
    }

}
