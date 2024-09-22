package com.example.Island.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "island_matrix")
public class IslandMatrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rows", nullable = false)
    private int rows;

    @Column(name = "cols", nullable = false)
    private int cols;

    @Column(name = "matrix", nullable = false)
    private String matrix;
}
