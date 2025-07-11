package CalorieTracker.service.mealingredient;

import CalorieTracker.entity.Ingredient;
import CalorieTracker.entity.Meal;
import CalorieTracker.entity.MealIngredient;

import java.util.List;

public interface MealIngredientService {
    List<MealIngredient> findByMeal(Meal meal);
    List<MealIngredient> findByIngredient(Ingredient ingredient);
    MealIngredient findById(Long id);
    MealIngredient save(MealIngredient mealIngredient);
    void deleteById(Long id);
}
