-- 1. Create schema (database) if it doesn't already exist
CREATE DATABASE IF NOT EXISTS calorietracker;

-- 2. Use the calorietracker schema
USE calorietracker;

-- 3. Drop the existing ingredient table if needed
DROP TABLE IF EXISTS ingredient;

-- 4. Create the ingredient table
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
