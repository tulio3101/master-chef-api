package edu.dosw.masterchef.model.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document(collection = "recipes")
@Data
@Builder
public class Recipe {

    @Id
    private Long id;

    private int season;

    private String title;

    private List<Ingredient> ingredients;

    private Chef chef;

    private List<String> steps;

}
