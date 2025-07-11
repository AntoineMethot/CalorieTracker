package CalorieTracker.dao;

import CalorieTracker.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    // This interface will automatically provide CRUD operations for Meal entity
    // No need to implement standard methods, JpaRepository provides them out of the box
    List<Meal> findByUsername(String username); // Method to find meals by username
}
