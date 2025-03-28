package com.smart_waste_manangement_system.Controller;

import com.smart_waste_manangement_system.model.User;
import com.smart_waste_manangement_system.repository.PickupRequestRepository;
import com.smart_waste_manangement_system.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class UserController {

    private UserRepository userRepo;

    private PickupRequestRepository pickupRequestRepo;
    @GetMapping("/login")
    public String loginForm() {
        return "login"; // Thymeleaf template: login.html [[1]]
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        User user = userRepo.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
        if (user != null) {
            return "redirect:/dashboard"; // Redirect based on role [[9]]
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalRequests", pickupRequestRepo.count());
        return "dashboard"; // templates/dashboard.html
    }


}