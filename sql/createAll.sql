-- Create schema (if not already)
CREATE DATABASE IF NOT EXISTS calorietracker;
USE calorietracker;

-- 1. Ingredient table
CREATE DATABASE IF NOT EXISTS calorietracker;DROP TABLE IF EXISTS ingredient;
CREATE TABLE ingredient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    portion_description VARCHAR(255) NOT NULL,
    grams_per_portion DOUBLE NOT NULL,
    calories_per_portion DOUBLE NOT NULL,
    protein_per_portion DOUBLE NOT NULL,
    carbs_per_portion DOUBLE NOT NULL,
    fat_per_portion DOUBLE NOT NULL
);

-- 2. Meal table (reusable meals made of ingredients)
DROP TABLE IF EXISTS meal;
CREATE TABLE meal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- 3. Link table: meal_ingredient (connects ingredients to meals)
DROP TABLE IF EXISTS meal_ingredient;
CREATE TABLE meal_ingredient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    meal_id BIGINT NOT NULL,
    ingredient_id BIGINT NOT NULL,
    portion_quantity DOUBLE NOT NULL,
    FOREIGN KEY (meal_id) REFERENCES meal(id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
);

-- 4. Meal entry log: meal eaten at a specific time
DROP TABLE IF EXISTS meal_entry;
CREATE TABLE meal_entry (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    meal_id BIGINT NOT NULL,
    eaten_at DATETIME NOT NULL,
    FOREIGN KEY (meal_id) REFERENCES meal(id)
);
