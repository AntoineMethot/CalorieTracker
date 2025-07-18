package CalorieTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entries")
public class EntryController {

    @GetMapping("/list")
    public String listEntries() {
        // Logic to retrieve and display entries
        // This method will return the view name for listing entries
        return "entries/list-entries"; // Assuming you have a Thymeleaf template at src/main/resources/templates/entries/list.html
    }
}
