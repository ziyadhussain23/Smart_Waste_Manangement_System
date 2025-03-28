package com.smart_waste_management_system.controller;

import com.smart_waste_management_system.model.PickupRequest;
import com.smart_waste_management_system.repository.PickupRequestRepository;
import com.smart_waste_management_system.repository.UserRepository;
import com.smart_waste_management_system.repository.WasteCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@AllArgsConstructor
@Controller
@RequestMapping("/requests")
public class PickupRequestController {

    private PickupRequestRepository requestRepo;

    private UserRepository userRepo;

    private WasteCategoryRepository categoryRepo;

    @GetMapping
    public String listRequests(Model model) {
        model.addAttribute("requests", requestRepo.findAll());
        return "request/list"; // List view [[6]]
    }

    @GetMapping("/new")
    public String createRequestForm(Model model) {
        model.addAttribute("request", new PickupRequest());
        model.addAttribute("users", userRepo.findAll()); // User dropdown
        model.addAttribute("categories", categoryRepo.findAll()); // Category dropdown [[5]]
        return "request/form";
    }

    @PostMapping("/save")
    public String saveRequest(@ModelAttribute PickupRequest request) {
        request.setStatus("Requested");
        requestRepo.save(request);
        return "redirect:/requests";
    }

    @GetMapping("/mark-collected/{id}")
    public String markCollected(@PathVariable int id) {
        PickupRequest request = requestRepo.findById(id).orElse(null);
        if (request != null) {
            request.setStatus("Collected");
            requestRepo.save(request);
        }
        return "redirect:/requests";
    }
}