package CalorieTracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "meal_ingredient", schema = "calorietracker")
public class MealIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Column(name = "portion_quantity", nullable = false)
    private Double portionQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Double getPortionQuantity() {
        return portionQuantity;
    }

    public void setPortionQuantity(Double portionQuantity) {
        this.portionQuantity = portionQuantity;
    }

    @Override
    public String toString() {
        return "MealIngredient{" +
                "id=" + id +
                ", meal=" + meal +
                ", ingredient=" + ingredient +
                ", portionQuantity=" + portionQuantity +
                '}';
    }
}