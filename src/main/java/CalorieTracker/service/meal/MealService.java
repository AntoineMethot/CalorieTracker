package CalorieTracker.service.meal;

import CalorieTracker.entity.Meal;

import java.util.List;

public interface MealService {
    List<Meal> findAll();
    Meal findById(Long id);
    Meal save(Meal meal);
    void deleteById(Long id);
    List<Meal> findByUsername(String username); // Added method to find meals by username
}
