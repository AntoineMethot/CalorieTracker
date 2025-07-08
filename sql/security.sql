USE `calorietracker`;

-- Inserting data for table `users`
-- NOTE: The passwords are encrypted using BCrypt
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
-- Default passwords here are: fun123
INSERT INTO `users`
VALUES
('camille','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('antoine','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1);

-- Inserting data for table `authorities`
INSERT INTO `authorities`
VALUES
('camille','ROLE_USER'),
('antoine','ROLE_USER'),
('antoine','ROLE_ADMIN');