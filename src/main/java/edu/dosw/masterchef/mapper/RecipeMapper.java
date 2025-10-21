package edu.dosw.masterchef.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import edu.dosw.masterchef.model.dto.Request.RecipeRequestDTO;
import edu.dosw.masterchef.model.dto.Response.RecipeResponseDTO;
import edu.dosw.masterchef.model.entity.Recipe;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    Recipe toEntity(RecipeRequestDTO dto);

    RecipeResponseDTO toDto(Recipe entity);

    List<Recipe> toEntityList(List<RecipeRequestDTO> dto);

    List<RecipeResponseDTO> toDtoList(List<Recipe> entity);
}
