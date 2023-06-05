package com.my.diplom.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "items")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "buy_price")
    private int buyPrice;

    @Column(name = "current_price")
    private int currentPrice;

    @Column(name = "status")
    private String status;
}
