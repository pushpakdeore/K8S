package com.example.user.service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users1")
public class Users {
    @Id
    @GeneratedValue
    private int id;

    private String firstName;

    private String lastName;

    private String gender;

    private String dob;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean isConfirmed;

    private String token;

}
