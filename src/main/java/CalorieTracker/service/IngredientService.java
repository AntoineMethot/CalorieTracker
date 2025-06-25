package CalorieTracker.service;

import CalorieTracker.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> findAll();
    Ingredient findById(Long id);
    Ingredient save(Ingredient ingredient);
    void deleteById(Long id);
}
