package com.example.Island.service;

import com.example.Island.persistence.dto.IslandDto;

public interface IslandService {
    public int countClosedIslands(IslandDto matrixDTO);
    public void saveIslandMatrix(IslandDto islandDto);
}
