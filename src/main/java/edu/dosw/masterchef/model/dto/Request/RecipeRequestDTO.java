package edu.dosw.masterchef.model.dto.Request;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipeRequestDTO {

    private Long id;

    private String title;

    private List<IngredientRequestDTO> ingredients;

    private ChefRequestDTO chef;

    private List<String> steps;

    private int season;

}
