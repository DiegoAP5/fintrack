package com.example.findtrack.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter @Setter
@Table(name = "savings")
public class Saving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float goal;

    private Float amountAdded;

    private Float withdraw;

    private Float amount;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
