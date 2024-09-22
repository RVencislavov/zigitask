package com.example.Island.mapper;

import com.example.Island.persistence.dto.IslandDto;
import com.example.Island.persistence.entity.IslandMatrix;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IslandMapper {

    IslandMapper INSTANCE = Mappers.getMapper(IslandMapper.class);

    @Mapping(target = "matrix", source = "matrix", qualifiedByName = "matrixToString")
    IslandMatrix toEntity(IslandDto dto);

    @Mapping(target = "matrix", source = "matrix", qualifiedByName = "stringToMatrix")
    IslandDto toDTO(IslandMatrix entity);

    @Named("matrixToString")
    default String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int value : row) {
                sb.append(value).append(",");
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 1); // Remove the last comma
            }
            sb.append(";");
        }
        return sb.toString();
    }

    @Named("stringToMatrix")
    default int[][] stringToMatrix(String matrixStr) {
        String[] rows = matrixStr.split(";");
        int[][] matrix = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String[] values = rows[i].split(",");
            matrix[i] = new int[values.length];
            for (int j = 0; j < values.length; j++) {
                matrix[i][j] = Integer.parseInt(values[j]);
            }
        }
        return matrix;
    }
}
