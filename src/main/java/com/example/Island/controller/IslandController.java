package com.example.Island.controller;

import com.example.Island.persistence.dto.IslandDto;
import com.example.Island.service.serviceImpl.IslandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/islands")
public class IslandController {

    @Autowired
    private IslandServiceImpl islandService;

    @PostMapping("/closedIslands")
    public int countClosedIslands(@RequestBody IslandDto islandDto) {
        islandService.saveIslandMatrix(islandDto);
        return islandService.countClosedIslands(islandDto);
    }
}
