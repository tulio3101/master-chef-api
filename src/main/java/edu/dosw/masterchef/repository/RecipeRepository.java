package edu.dosw.masterchef.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.dosw.masterchef.model.entity.Recipe;
import edu.dosw.masterchef.model.entity.enums.Role;

public interface RecipeRepository extends MongoRepository<Recipe, Long> {

    Optional<Recipe> findById(Long id);

    List<Recipe> findAll();

    List<Recipe> findByChefRole(Role role);

    List<Recipe> findBySeason(int season);

    List<Recipe> findByIngredientsName(String name);

}
