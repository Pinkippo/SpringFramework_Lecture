package inhatc.spring.shop.entity;

import jakarta.persistence.*;

@Entity
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;




}
