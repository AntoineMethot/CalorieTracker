package CalorieTracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // No need for @Column as JPA automatically maps camelCase to snake_case
    // Columns are already NOT NULL so no need for @Column(nullable = false)
    private String portionDescription;
    private Double gramsPerPortion;
    private Double caloriesPerPortion;
    private Double proteinPerPortion;
    private Double carbsPerPortion;
    private Double fatPerPortion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortionDescription() {
        return portionDescription;
    }

    public void setPortionDescription(String portionDescription) {
        this.portionDescription = portionDescription;
    }

    public Double getGramsPerPortion() {
        return gramsPerPortion;
    }

    public void setGramsPerPortion(Double gramsPerPortion) {
        this.gramsPerPortion = gramsPerPortion;
    }

    public Double getCaloriesPerPortion() {
        return caloriesPerPortion;
    }

    public void setCaloriesPerPortion(Double caloriesPerPortion) {
        this.caloriesPerPortion = caloriesPerPortion;
    }

    public Double getProteinPerPortion() {
        return proteinPerPortion;
    }

    public void setProteinPerPortion(Double proteinPerPortion) {
        this.proteinPerPortion = proteinPerPortion;
    }

    public Double getCarbsPerPortion() {
        return carbsPerPortion;
    }

    public void setCarbsPerPortion(Double carbsPerPortion) {
        this.carbsPerPortion = carbsPerPortion;
    }

    public Double getFatPerPortion() {
        return fatPerPortion;
    }

    public void setFatPerPortion(Double fatPerPortion) {
        this.fatPerPortion = fatPerPortion;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", portionDescription='" + portionDescription + '\'' +
                ", gramsPerPortion=" + gramsPerPortion +
                ", caloriesPerPortion=" + caloriesPerPortion +
                ", proteinPerPortion=" + proteinPerPortion +
                ", carbsPerPortion=" + carbsPerPortion +
                ", fatPerPortion=" + fatPerPortion +
                '}';
    }
}