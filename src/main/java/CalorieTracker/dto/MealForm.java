package CalorieTracker.dto;

import CalorieTracker.entity.Meal;
import CalorieTracker.entity.MealIngredient;

import java.util.ArrayList;
import java.util.List;

public class MealForm {

    private Meal meal = new Meal();  // Core meal info
    private List<MealIngredient> mealIngredients = new ArrayList<>();  // List of ingredients + portions

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public List<MealIngredient> getMealIngredients() {
        return mealIngredients;
    }

    public void setMealIngredients(List<MealIngredient> mealIngredients) {
        this.mealIngredients = mealIngredients;
    }
}
