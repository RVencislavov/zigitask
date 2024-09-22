package com.example.Island.service.serviceImpl;

import com.example.Island.mapper.IslandMapper;
import com.example.Island.persistence.dto.IslandDto;
import com.example.Island.persistence.entity.IslandMatrix;
import com.example.Island.repository.IslandRepository;
import com.example.Island.service.IslandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IslandServiceImpl implements IslandService {

    private final IslandRepository islandMatrixRepository;
    private final IslandMapper islandMapper;

    @Autowired
    public IslandServiceImpl(IslandRepository islandMatrixRepository, IslandMapper islandMapper) {
        this.islandMatrixRepository = islandMatrixRepository;
        this.islandMapper = islandMapper;
    }

    public int countClosedIslands(IslandDto matrixDTO) {
        int[][] matrix = matrixDTO.getMatrix();
        int rows = matrixDTO.getRows();
        int cols = matrixDTO.getCols();
        boolean[][] visited = new boolean[rows][cols];

        int closedIslands = 0;

        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    if (isClosedIsland(matrix, visited, i, j, rows, cols)) {
                        closedIslands++;
                    }
                }
            }
        }

        return closedIslands;
    }

    private boolean isClosedIsland(int[][] matrix, boolean[][] visited, int x, int y, int rows, int cols) {
        if (x < 0 || y < 0 || x >= rows || y >= cols) {
            return false;
        }

        if (matrix[x][y] == 0 || visited[x][y]) {
            return true;
        }

        visited[x][y] = true;

        boolean up = isClosedIsland(matrix, visited, x - 1, y, rows, cols);
        boolean down = isClosedIsland(matrix, visited, x + 1, y, rows, cols);
        boolean left = isClosedIsland(matrix, visited, x, y - 1, rows, cols);
        boolean right = isClosedIsland(matrix, visited, x, y + 1, rows, cols);

        return up && down && left && right;
    }


    public void saveIslandMatrix(IslandDto islandDto) {
        IslandMatrix islandMatrix = islandMapper.toEntity(islandDto);
        String matrixAsString = convertMatrixToString(islandDto.getMatrix());
        islandMatrix.setMatrix(matrixAsString);
        islandMatrixRepository.save(islandMatrix);
    }

    private String convertMatrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int value : row) {
                sb.append(value);
            }
            sb.append(";");
        }
        return sb.toString();
    }
}
