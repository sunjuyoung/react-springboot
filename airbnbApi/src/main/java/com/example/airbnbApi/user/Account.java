package com.example.airbnbApi.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;



}
