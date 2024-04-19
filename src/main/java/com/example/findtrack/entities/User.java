package com.example.findtrack.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Saving>savings;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Payment>payments;

    @ManyToMany(mappedBy = "users")
    private List<SharedSaving>sharedSavings;
}
