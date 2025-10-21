package edu.dosw.masterchef.model.dto.Response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipeResponseDTO {

    private Long id;

    private String title;

    private List<IngredientResponseDTO> ingredients;

    private ChefResponseDTO chef;

    private List<String> steps;

    private int season;

}
