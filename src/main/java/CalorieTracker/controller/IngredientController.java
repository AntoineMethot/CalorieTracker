package CalorieTracker.controller;

import CalorieTracker.entity.Ingredient;
import CalorieTracker.service.ingredient.IngredientService;
import CalorieTracker.service.meal.MealService;
import CalorieTracker.service.mealingredient.MealIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;
    private final MealIngredientService mealIngredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService, MealIngredientService mealIngredientService) {
        this.ingredientService = ingredientService;
        this.mealIngredientService = mealIngredientService;
    }

    @GetMapping("/list")
    public String listIngredients(Model model) {
        // Get the current authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        //Find list of ingredients for the current user
        List<Ingredient> ingredients = ingredientService.findByUsername(currentUsername);

        model.addAttribute("ingredients", ingredients);
        return "ingredients/list-ingredients"; // Thymeleaf template path
    }

    @GetMapping("/addIngredient")
    public String addIngredient(Model model) {
        Ingredient ingredient = new Ingredient(); // Create a new ingredient object
        model.addAttribute("ingredient", ingredient);
        return "ingredients/ingredient-form"; // Thymeleaf template path for the form
    }

    @GetMapping("/editIngredient/{id}")
    public String editIngredient(@PathVariable Long id, Model model) {
        Ingredient ingredient = ingredientService.findById(id);
        model.addAttribute("ingredient", ingredient);
        return "ingredients/ingredient-form"; // Thymeleaf template path for the form
    }

    @GetMapping("/deleteIngredient/{id}")
    public String deleteIngredient(@PathVariable Long id, Model model) {
        Ingredient ingredient = ingredientService.findById(id);

        if (mealIngredientService.findByIngredient(ingredient) == null) {
            ingredientService.deleteById(id);
            // Redirect to the list after deletion
            return "redirect:/ingredients/list";
        }
        else {
            // If the ingredient is used in a meal, you might want to handle this case differently
            model.addAttribute("errorMessage", "Ingredient is used in a meal and cannot be deleted.");
            return listIngredients(model);
        }
    }

    @PostMapping("/save")
    public String saveIngredient(@ModelAttribute("ingredient") Ingredient ingredient) {
        // Get the current authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        // Set the username on the ingredient
        ingredient.setUsername(currentUsername);
        ingredientService.save(ingredient);
        return "redirect:/ingredients/list"; // Redirect to the list after saving
    }
}
