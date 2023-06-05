package com.my.diplom.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "item_name")
    private String  itemName;

    @Column(name = "start_price")
    private int startPrice;

    @Column(name = "price")
    private int price;

    @Column(name = "type")
    private String type;

    @Column(name = "dispersion")
    private int dispersion;
}
