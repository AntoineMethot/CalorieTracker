package CalorieTracker.dao;

import CalorieTracker.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    // This interface will automatically provide CRUD operations for Ingredient entity
    // No need to implement methods, JpaRepository provides them out of the box
}
