-- Create schema
CREATE DATABASE IF NOT EXISTS calorietracker;
USE calorietracker;

-- 1. users table (Spring Security compatible)
DROP TABLE IF EXISTS users;
CREATE TABLE users (
                       username VARCHAR(50) NOT NULL PRIMARY KEY,
                       password VARCHAR(255) NOT NULL,
                       enabled BOOLEAN NOT NULL
);

-- 2. authorities table
DROP TABLE IF EXISTS authorities;
CREATE TABLE authorities (
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             CONSTRAINT fk_authority_username FOREIGN KEY (username) REFERENCES users(username)
);
CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

-- 3. Ingredient table
DROP TABLE IF EXISTS ingredient;
CREATE TABLE ingredient (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            username VARCHAR(50) NOT NULL,
                            name VARCHAR(255) NOT NULL,
                            portion_description VARCHAR(255) NOT NULL,
                            quantity_per_portion DOUBLE NOT NULL,
                            calories_per_portion DOUBLE NOT NULL,
                            protein_per_portion DOUBLE NOT NULL,
                            carbs_per_portion DOUBLE NOT NULL,
                            fat_per_portion DOUBLE NOT NULL,
                            image_url VARCHAR(2048),
                            FOREIGN KEY (username) REFERENCES users(username)
);

-- 4. Meal table
DROP TABLE IF EXISTS meal;
CREATE TABLE meal (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL,
                      name VARCHAR(255) NOT NULL,
                      image_url VARCHAR(2048),
                      FOREIGN KEY (username) REFERENCES users(username)
);

-- 5. meal_ingredient link table
DROP TABLE IF EXISTS meal_ingredient;
CREATE TABLE meal_ingredient (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 meal_id BIGINT NOT NULL,
                                 ingredient_id BIGINT NOT NULL,
                                 portion_quantity DOUBLE NOT NULL,
                                 FOREIGN KEY (meal_id) REFERENCES meal(id),
                                 FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
);

-- 6. Meal entry log
DROP TABLE IF EXISTS meal_entry;
CREATE TABLE meal_entry (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            username VARCHAR(50) NOT NULL,
                            meal_id BIGINT NOT NULL,
                            eaten_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (username) REFERENCES users(username),
                            FOREIGN KEY (meal_id) REFERENCES meal(id)
);

-- 7. Tag table
DROP TABLE IF EXISTS tag;
CREATE TABLE tag (
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     username VARCHAR(50) NOT NULL,
                     name VARCHAR(50) NOT NULL,
                     FOREIGN KEY (username) REFERENCES users(username)
);

-- 8. meal_tag link table
DROP TABLE IF EXISTS meal_tag;
CREATE TABLE meal_tag (
                          meal_id BIGINT NOT NULL,
                          tag_id BIGINT NOT NULL,
                          PRIMARY KEY (meal_id, tag_id),
                          FOREIGN KEY (meal_id) REFERENCES meal(id),
                          FOREIGN KEY (tag_id) REFERENCES tag(id)
);
