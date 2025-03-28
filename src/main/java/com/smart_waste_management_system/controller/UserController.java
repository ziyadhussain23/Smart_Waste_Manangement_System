// File: src/main/java/com/smart_waste_management_system/controller/UserController.java
package com.smart_waste_management_system.controller;

import com.smart_waste_management_system.model.User;
import com.smart_waste_management_system.repository.PickupRequestRepository;
import com.smart_waste_management_system.repository.UserRepository;
import com.smart_waste_management_system.repository.ComplaintRepository;
import com.smart_waste_management_system.repository.CollectionScheduleRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class UserController {

    private UserRepository userRepo;
    private PickupRequestRepository pickupRequestRepo;
    private ComplaintRepository complaintRepo;
    private CollectionScheduleRepository scheduleRepo;

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // Thymeleaf template: login.html
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        User user = userRepo.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
        if (user != null) {
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalRequests", pickupRequestRepo.count());
        model.addAttribute("totalComplaints", complaintRepo.count());
        model.addAttribute("totalSchedules", scheduleRepo.count());
        return "dashboard"; // Thymeleaf template: dashboard.html
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login?logout";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup"; // Thymeleaf template: signup.html
    }

    @PostMapping("/signup")
    public String signupUser(@ModelAttribute User user) {
        userRepo.insert(user);
        return "redirect:/login";
    }
}