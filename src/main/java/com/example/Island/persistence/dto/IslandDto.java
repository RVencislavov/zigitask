package com.example.Island.persistence.dto;

import lombok.Data;

@Data
public class IslandDto {
    private int rows;
    private int cols;
    private int[][] matrix;
}
