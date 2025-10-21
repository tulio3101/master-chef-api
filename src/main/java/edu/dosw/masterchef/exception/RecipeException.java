package edu.dosw.masterchef.exception;

public class RecipeException extends RuntimeException {

    public RecipeException(String message) {
        super(message);
    }

    public static RecipeException create(Long id) {
        return new RecipeException(String.format("Recipe with this ID '%s' doesnt exist", id));
    }

}
