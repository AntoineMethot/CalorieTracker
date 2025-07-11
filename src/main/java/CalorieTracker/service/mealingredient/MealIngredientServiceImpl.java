package CalorieTracker.service.mealingredient;

import CalorieTracker.dao.MealIngredientRepository;
import CalorieTracker.entity.Ingredient;
import CalorieTracker.entity.Meal;
import CalorieTracker.entity.MealIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealIngredientServiceImpl implements MealIngredientService {

    private final MealIngredientRepository mealIngredientRepository;

    @Autowired
    public MealIngredientServiceImpl(MealIngredientRepository mealIngredientRepository) {
        this.mealIngredientRepository = mealIngredientRepository;
    }

    @Override
    public List<MealIngredient> findByMeal(Meal meal) {
        return mealIngredientRepository.findByMeal(meal);
    }

    @Override
    public List<MealIngredient> findByIngredient(Ingredient ingredient) {
        return mealIngredientRepository.findByIngredient(ingredient);
    }

    @Override
    public MealIngredient findById(Long id) {
        return mealIngredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Did not find meal ingredient id - " + id));
    }

    @Override
    public MealIngredient save(MealIngredient mealIngredient) {
        return mealIngredientRepository.save(mealIngredient);
    }

    @Override
    public void deleteById(Long id) {
        if (!mealIngredientRepository.existsById(id)) {
            throw new RuntimeException("Did not find meal ingredient id - " + id);
        }
        mealIngredientRepository.deleteById(id);
    }
}
