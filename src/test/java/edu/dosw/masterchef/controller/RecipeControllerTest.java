package edu.dosw.masterchef.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.dosw.masterchef.exception.RecipeException;
import edu.dosw.masterchef.model.dto.Request.ChefRequestDTO;
import edu.dosw.masterchef.model.dto.Request.IngredientRequestDTO;
import edu.dosw.masterchef.model.dto.Request.RecipeRequestDTO;
import edu.dosw.masterchef.model.dto.Response.ChefResponseDTO;
import edu.dosw.masterchef.model.dto.Response.IngredientResponseDTO;
import edu.dosw.masterchef.model.dto.Response.RecipeResponseDTO;
import edu.dosw.masterchef.service.RecipeService;

@WebMvcTest(controllers = RecipeController.class)
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RecipeService recipeService;

    @TestConfiguration
    static class MockConfig {
        @Bean
        RecipeService recipeService() {
            return Mockito.mock(RecipeService.class);
        }
    }

    @Test
    @DisplayName("a) Registrar una receta como CONTESTANT devuelve 201 y el cuerpo esperado")
    void createContestantRecipeShouldReturn201WithBody() throws Exception {
        RecipeRequestDTO request = RecipeRequestDTO.builder()
                .id(1L)
                .title("Pasta Bolognese")
                .ingredients(List.of(
                        IngredientRequestDTO.builder().name("pasta").build(),
                        IngredientRequestDTO.builder().name("tomato").build()))
                .chef(ChefRequestDTO.builder().name("Gordon").build())
                .steps(List.of("Boil pasta", "Cook sauce"))
                .season(1)
                .build();

        RecipeResponseDTO response = RecipeResponseDTO.builder()
                .id(1L)
                .title("Pasta Bolognese")
                .ingredients(List.of(
                        IngredientResponseDTO.builder().name("pasta").build(),
                        IngredientResponseDTO.builder().name("tomato").build()))
                .chef(ChefResponseDTO.builder().name("Gordon").build())
                .steps(List.of("Boil pasta", "Cook sauce"))
                .season(1)
                .build();

        Mockito.when(recipeService.createRequestContestant(any(RecipeRequestDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/recipes/contestant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Pasta Bolognese")))
                .andExpect(jsonPath("$.season", is(1)))
                .andExpect(jsonPath("$.ingredients", hasSize(2)))
                .andExpect(jsonPath("$.ingredients[*].name", containsInAnyOrder("pasta", "tomato")))
                .andExpect(jsonPath("$.chef.name", is("Gordon")))
                .andExpect(jsonPath("$.steps", hasSize(2)));
    }

    @Test
    @DisplayName("b) Búsqueda por ingrediente devuelve resultados correctos")
    void searchByIngredientShouldReturnMatchingRecipes() throws Exception {
        String ingredient = "tomato";
        RecipeResponseDTO r1 = RecipeResponseDTO.builder()
                .id(10L)
                .title("Tomato Soup")
                .ingredients(List.of(
                        IngredientResponseDTO.builder().name("tomato").build(),
                        IngredientResponseDTO.builder().name("onion").build()))
                .chef(ChefResponseDTO.builder().name("Alice").build())
                .steps(List.of("Blend", "Heat"))
                .season(2)
                .build();

        RecipeResponseDTO r2 = RecipeResponseDTO.builder()
                .id(11L)
                .title("Bruschetta")
                .ingredients(List.of(
                        IngredientResponseDTO.builder().name("bread").build(),
                        IngredientResponseDTO.builder().name("tomato").build()))
                .chef(ChefResponseDTO.builder().name("Bob").build())
                .steps(List.of("Toast", "Top"))
                .season(2)
                .build();

        Mockito.when(recipeService.getRecipeByIngredient(ingredient))
                .thenReturn(List.of(r1, r2));

        mockMvc.perform(get("/recipes/name/{name}", ingredient))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].ingredients[*].name", hasItem("tomato")))
                .andExpect(jsonPath("$[1].ingredients[*].name", hasItem("tomato")));
    }

    @Test
    @DisplayName("c) Consultar recetas por temporada inexistente devuelve 404")
    void getBySeasonWhenNoRecipesShouldReturnError() throws Exception {
        int season = 99;
        Mockito.when(recipeService.getRecipeBySeason(season))
                .thenThrow(new RecipeException("Dont have recipes for this seasons"));

        mockMvc.perform(get("/recipes/season/{season}", season))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Dont have recipes for this seasons")));
    }
}
