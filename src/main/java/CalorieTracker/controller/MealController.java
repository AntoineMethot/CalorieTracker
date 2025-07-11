package CalorieTracker.controller;

import CalorieTracker.dto.MealForm;
import CalorieTracker.entity.Meal;
import CalorieTracker.entity.MealIngredient;
import CalorieTracker.service.ingredient.IngredientService;
import CalorieTracker.service.meal.MealService;
import CalorieTracker.service.mealingredient.MealIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/meals")
public class MealController {

    private final MealService mealService;
    private final MealIngredientService mealIngredientService;
    private final IngredientService ingredientService;

    @Autowired
    public MealController(MealService mealService,
                          MealIngredientService mealIngredientService, IngredientService ingredientService) {
        this.mealService = mealService;
        this.mealIngredientService = mealIngredientService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/list")
    public String listMeals(Model model) {
        // Logic to retrieve and display meals
        // Get the current authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Find list of meals for the current user
        List<Meal> meals = mealService.findByUsername(currentUsername);
        model.addAttribute("meals", meals);

        return "meals/list-meals"; // Thymeleaf template path for the meal list page
    }

    @GetMapping("/addMeal")
    public String showMealForm(Model model) {
        MealForm mealForm = new MealForm();

        // Add one blank ingredient by default
        mealForm.getMealIngredients().add(new MealIngredient());

        model.addAttribute("mealForm", mealForm);
        model.addAttribute("allIngredients", ingredientService.findAll());
        return "meals/meal-form";  // match the name above
    }

    @PostMapping("/save")
    public String saveMeal(@ModelAttribute MealForm mealForm) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Meal meal = mealForm.getMeal();
        meal.setUsername(currentUsername);

        Meal savedMeal = mealService.save(meal);

        for (MealIngredient mi : mealForm.getMealIngredients()) {
            // Re-fetch the actual Ingredient entity using the ID
            Long ingredientId = mi.getIngredient().getId();
            mi.setIngredient(ingredientService.findById(ingredientId));
            mi.setMeal(savedMeal);
            mealIngredientService.save(mi);
        }

        return "redirect:/meals/list";
    }


}
