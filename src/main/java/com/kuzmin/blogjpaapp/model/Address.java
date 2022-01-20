package com.kuzmin.blogjpaapp.model;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    @OneToOne(mappedBy = "address")
    private User user;
}
