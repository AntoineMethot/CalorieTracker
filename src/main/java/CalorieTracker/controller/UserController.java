package CalorieTracker.controller;

import CalorieTracker.entity.Ingredient;
import CalorieTracker.entity.User;
import CalorieTracker.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        // Fetch the list of users from the userService
        model.addAttribute("users", userService.findAll());
        return "users/list-users"; // Thymeleaf template path for listing users
    }

    @GetMapping("/addUser")
    public String addIngredient(Model model) {
        User user = new User(); // Create a new user object
        user.setRoles(new ArrayList<>());
        model.addAttribute("user", user);
        return "users/user-form"; // Thymeleaf template path for the form
    }

    @GetMapping("/deleteUser/{username}")
    public String deleteUser(@PathVariable String username) {
        userService.deleteByUsername(username);
        return "redirect:/users/list"; // Redirect to the list after deletion
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users/list"; // Redirect to the list after saving
    }

    @GetMapping("/editUser/{username}")
    public String editUser(@PathVariable String username, Model model) {
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "users/user-form"; // Thymeleaf template path for the form
    }
}
