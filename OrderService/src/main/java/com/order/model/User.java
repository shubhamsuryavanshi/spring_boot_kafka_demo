package com.order.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "user_table")
public class User extends BaseClass  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    @Email
    String email;

    public User() { }

    public User(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public @NotNull String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull String lastName) {
        this.lastName = lastName;
    }

    public @NotNull @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Email String email) {
        this.email = email;
    }
}
