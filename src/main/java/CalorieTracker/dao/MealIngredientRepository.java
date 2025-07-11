package CalorieTracker.dao;

import CalorieTracker.entity.Ingredient;
import CalorieTracker.entity.Meal;
import CalorieTracker.entity.MealIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealIngredientRepository extends JpaRepository<MealIngredient, Long> {
    // This interface will automatically provide CRUD operations for MealIngredient entity
    // No need to implement standard methods, JpaRepository provides them out of the box
    List<MealIngredient> findByMeal(Meal meal);
    List<MealIngredient> findByIngredient(Ingredient ingredient);
}
