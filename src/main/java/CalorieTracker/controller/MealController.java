package CalorieTracker.controller;

import CalorieTracker.entity.Meal;
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

    @Autowired
    public MealController(MealService mealService,
                          MealIngredientService mealIngredientService) {
        this.mealService = mealService;
        this.mealIngredientService = mealIngredientService;
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
    public String addMeal(Model model) {
        Meal meal = new Meal(); // Create a new ingredient object
        model.addAttribute("meal", meal);
        return "meals/meals-form"; // Thymeleaf template path for the form
    }
    @PostMapping("/save")
    public String saveMeal(@ModelAttribute("meal") Meal meal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        meal.setUsername(currentUsername); // assign current user
        mealService.save(meal);
        return "redirect:/meals/list";
    }


}
