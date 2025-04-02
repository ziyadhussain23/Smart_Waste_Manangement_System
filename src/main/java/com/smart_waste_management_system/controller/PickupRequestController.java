package com.smart_waste_management_system.controller;

import com.smart_waste_management_system.model.PickupRequest;
import com.smart_waste_management_system.repository.PickupRequestRepository;
import com.smart_waste_management_system.repository.UserRepository;
import com.smart_waste_management_system.repository.WasteCategoryRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        return "request/list";
    }

    @GetMapping("/new")
    public String createRequestForm(Model model) {
        model.addAttribute("request", new PickupRequest());
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("categories", categoryRepo.findAll());
        return "request/form";
    }

    @PostMapping("/save")
    public String saveRequest(@Valid @ModelAttribute("request") PickupRequest request, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("users", userRepo.findAll());
            model.addAttribute("categories", categoryRepo.findAll());
            return "request/form";
        }
        request.setStatus("Requested");
        if (request.getRequest_id() == null) {
            requestRepo.insert(request);
        } else {
            requestRepo.update(request);
        }
        return "redirect:/requests";
    }

    @GetMapping("/mark-collected/{id}")
    public String markCollected(@PathVariable int id) {
        requestRepo.findById(id).ifPresent(request -> {
            request.setStatus("Collected");
            requestRepo.update(request);
        });
        return "redirect:/requests";
    }
}