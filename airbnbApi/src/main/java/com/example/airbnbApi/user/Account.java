package com.example.airbnbApi.user;

import com.example.airbnbApi.common.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account  extends BaseTime {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String emailVerified;

    private String phoneNumber;

    private boolean social;

    private String image;

    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Role role;



}
