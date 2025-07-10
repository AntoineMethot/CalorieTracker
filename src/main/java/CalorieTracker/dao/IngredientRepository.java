package CalorieTracker.dao;

import CalorieTracker.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    // This interface will automatically provide CRUD operations for Ingredient entity
    // No need to implement standard methods, JpaRepository provides them out of the box

    List<Ingredient> findByUsername(String username);
}
