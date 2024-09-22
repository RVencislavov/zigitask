package com.example.Island.repository;

import com.example.Island.persistence.entity.IslandMatrix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IslandRepository extends JpaRepository<IslandMatrix, Long> {
}
