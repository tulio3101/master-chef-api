package edu.dosw.masterchef.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dosw.masterchef.model.dto.Request.RecipeRequestDTO;
import edu.dosw.masterchef.model.dto.Response.RecipeResponseDTO;
import edu.dosw.masterchef.model.entity.enums.Role;
import edu.dosw.masterchef.service.RecipeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
@Tag(name = "Recipe", description = "Endpoints for Recipe operations")
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping("/contestant")
    public ResponseEntity<RecipeResponseDTO> createContestant(
            @Valid @RequestBody RecipeRequestDTO dto) {

        RecipeResponseDTO created = recipeService.createRequestContestant(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/viewer")
    public ResponseEntity<RecipeResponseDTO> createViewer(
            @Valid @RequestBody RecipeRequestDTO dto) {

        RecipeResponseDTO created = recipeService.createRequestViewer(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/jury")
    public ResponseEntity<RecipeResponseDTO> createJury(
            @Valid @RequestBody RecipeRequestDTO dto) {

        RecipeResponseDTO created = recipeService.createRequestJury(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponseDTO> updateRecipe(
            @Parameter(description = "Recipe to be updated", required = true) @PathVariable Long id,
            @Valid @RequestBody RecipeRequestDTO dto) {

        RecipeResponseDTO recipeUpdated = recipeService.updateRecipe(id, dto);

        return ResponseEntity.ok(recipeUpdated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(
            @Parameter(description = "Recipe to be deleted", required = true) @PathVariable Long id) {

        recipeService.deleteRecipe(id);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/allRecipes")
    public ResponseEntity<List<RecipeResponseDTO>> getAllRecipes() {

        return ResponseEntity.ok(recipeService.getAllRecipe());

    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<RecipeResponseDTO>> getRecipeByRole(@PathVariable Role role) {

        return ResponseEntity.ok(recipeService.getRecipeByRole(role));

    }

    @GetMapping("/season/{season}")
    public ResponseEntity<List<RecipeResponseDTO>> getRecipeBySeason(@PathVariable int season) {

        return ResponseEntity.ok(recipeService.getRecipeBySeason(season));

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<RecipeResponseDTO>> getRecipeByIngredientName(@PathVariable String name) {

        return ResponseEntity.ok(recipeService.getRecipeByIngredient(name));

    }

}
