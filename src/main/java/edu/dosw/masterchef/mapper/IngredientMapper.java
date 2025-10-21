package edu.dosw.masterchef.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import edu.dosw.masterchef.model.dto.Request.IngredientRequestDTO;
import edu.dosw.masterchef.model.dto.Response.IngredientResponseDTO;
import edu.dosw.masterchef.model.entity.Ingredient;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    Ingredient toEntity(IngredientRequestDTO dto);

    IngredientResponseDTO toDto(Ingredient entity);

    List<Ingredient> toEntityList(List<IngredientRequestDTO> dto);

    List<IngredientResponseDTO> toDtoList(List<Ingredient> entity);
}
