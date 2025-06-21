package com.krfootwear.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data  // Lombok annotation that generates getters, setters, toString, equals, and hashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
}
