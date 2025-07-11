package CalorieTracker.controller;

import CalorieTracker.entity.Ingredient;
import CalorieTracker.service.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IngredientAPIController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientAPIController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredients")
    public List<Ingredient> findAllIngredients() {
        return ingredientService.findAll();
    }

    @GetMapping("/ingredients/{id}")
    public Ingredient findIngredientById(@PathVariable Long id) {
        return ingredientService.findById(id);
    }

    @PostMapping("/ingredients")
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
        ingredient.setId(null); // Ensure a new ingredient is created
        return ingredientService.save(ingredient);
    }

    @PutMapping("/ingredients/{id}")
    public Ingredient updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        // Throws if not found
        ingredientService.findById(id);

        ingredient.setId(id); // Force correct ID
        return ingredientService.save(ingredient);
    }

    @DeleteMapping("/ingredients/{id}")
    public void deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteById(id);
    }
}
