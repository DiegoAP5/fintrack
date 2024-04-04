package com.example.findtrack.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@Table(name = "sharedsavings")
public class SharedSaving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float goal;

    private Float amountAdded;

    private Float withdraw;

    private Float amount;

    @ManyToMany
    @JoinTable(name = "shared", joinColumns = @JoinColumn(name = "shared_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User>users;
}
