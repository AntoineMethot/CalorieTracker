package CalorieTracker.controller;

import CalorieTracker.entity.Ingredient;
import CalorieTracker.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/list")
    public String listIngredients(Model model) {
        List<Ingredient> ingredients = ingredientService.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredients/list-ingredients"; // Thymeleaf template path
    }

    @GetMapping("/showAddIngredientForm")
    public String showAddIngredientForm(Model model) {
        Ingredient ingredient = new Ingredient(); // Create a new ingredient object
        model.addAttribute("ingredient", ingredient);
        return "ingredients/ingredient-form"; // Thymeleaf template path for the form
    }

    @PostMapping("/save")
    public String saveIngredient(@ModelAttribute("ingredient") Ingredient ingredient) {
        ingredientService.save(ingredient);
        return "redirect:/ingredients/list"; // Redirect to the list after saving
    }
}
