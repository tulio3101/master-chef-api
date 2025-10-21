package edu.dosw.masterchef.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.dosw.masterchef.exception.RecipeException;
import edu.dosw.masterchef.mapper.ChefMapper;
import edu.dosw.masterchef.mapper.IngredientMapper;
import edu.dosw.masterchef.mapper.RecipeMapper;
import edu.dosw.masterchef.model.dto.Request.ChefRequestDTO;
import edu.dosw.masterchef.model.dto.Request.RecipeRequestDTO;
import edu.dosw.masterchef.model.dto.Response.RecipeResponseDTO;
import edu.dosw.masterchef.model.entity.Chef;
import edu.dosw.masterchef.model.entity.Recipe;
import edu.dosw.masterchef.model.entity.enums.Role;
import edu.dosw.masterchef.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final IngredientMapper ingredientMapper;
    private final ChefMapper chefMapper;

    @Transactional
    public RecipeResponseDTO createRequestContestant(RecipeRequestDTO dto) {

        Chef chef = Chef.builder()
                .name(dto.getChef().getName())
                .role(Role.CONTESTANT)
                .build();

        Recipe recipe = Recipe.builder()
                .id(dto.getId())
                .season(dto.getSeason())
                .title(dto.getTitle())
                .ingredients(ingredientMapper.toEntityList(dto.getIngredients()))
                .chef(chef)
                .steps(dto.getSteps())
                .build();

        Recipe saved = recipeRepository.save(recipe);

        return recipeMapper.toDto(saved);

    }

    @Transactional
    public RecipeResponseDTO createRequestViewer(RecipeRequestDTO dto) {

        Chef chef = Chef.builder()
                .name(dto.getChef().getName())
                .role(Role.VIEWER)
                .build();

        Recipe recipe = Recipe.builder()
                .id(dto.getId())
                .season(dto.getSeason())
                .title(dto.getTitle())
                .ingredients(ingredientMapper.toEntityList(dto.getIngredients()))
                .chef(chef)
                .steps(dto.getSteps())
                .build();

        Recipe saved = recipeRepository.save(recipe);

        return recipeMapper.toDto(saved);

    }

    @Transactional
    public RecipeResponseDTO createRequestJury(RecipeRequestDTO dto) {

        Chef chef = Chef.builder()
                .name(dto.getChef().getName())
                .role(Role.JURY)
                .build();

        Recipe recipe = Recipe.builder()
                .id(dto.getId())
                .season(dto.getSeason())
                .title(dto.getTitle())
                .ingredients(ingredientMapper.toEntityList(dto.getIngredients()))
                .chef(chef)
                .steps(dto.getSteps())
                .build();

        Recipe saved = recipeRepository.save(recipe);

        return recipeMapper.toDto(saved);

    }

    @Transactional
    public void deleteRecipe(Long id) {

        if (!recipeRepository.existsById(id)) {
            throw new RecipeException("Dont Exist this Recipe");
        }

        recipeRepository.deleteById(id);

    }

    @Transactional
    public RecipeResponseDTO updateRecipe(Long id, RecipeRequestDTO dto) {

        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> RecipeException.create(id));
        recipe.setSeason(dto.getSeason());
        recipe.setTitle(dto.getTitle());
        recipe.setIngredients(ingredientMapper.toEntityList(dto.getIngredients()));
        recipe.setChef(chefMapper.toEntity(dto.getChef()));
        recipe.setSteps(dto.getSteps());

        Recipe updated = recipeRepository.save(recipe);

        return recipeMapper.toDto(updated);

    }

    public List<RecipeResponseDTO> getAllRecipe() {

        List<Recipe> recipes = recipeRepository.findAll();

        return recipeMapper.toDtoList(recipes);

    }

    public List<RecipeResponseDTO> getRecipeByRole(Role role) {

        List<Recipe> recipes = recipeRepository.findByChefRole(role);

        return recipeMapper.toDtoList(recipes);
    }

    public List<RecipeResponseDTO> getRecipeBySeason(int season) {

        List<Recipe> recipes = recipeRepository.findBySeason(season);

        if (recipes.isEmpty()) {
            throw new RecipeException("Dont have recipes for this seasons");
        }

        return recipeMapper.toDtoList(recipes);

    }

    public List<RecipeResponseDTO> getRecipeByIngredient(String name) {

        List<Recipe> recipes = recipeRepository.findByIngredientsName(name);

        return recipeMapper.toDtoList(recipes);

    }

}
