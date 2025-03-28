package com.smart_waste_management_system.controller;

import com.smart_waste_management_system.model.RecyclingCenter;
import com.smart_waste_management_system.repository.RecyclingCenterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/centers")
public class RecyclingCenterController {

    private RecyclingCenterRepository centerRepo;

    @GetMapping
    public String listCenters(Model model) {
        model.addAttribute("centers", centerRepo.findAll());
        return "center/list"; // List view [[6]]
    }

    @GetMapping("/new")
    public String createCenterForm(Model model) {
        model.addAttribute("center", new RecyclingCenter());
        return "center/form"; // Form template [[5]]
    }

    @PostMapping("/save")
    public String saveCenter(@ModelAttribute RecyclingCenter center) {
        if (center.getCenter_id() == null) {
            centerRepo.insert(center);
        } else {
            centerRepo.update(center);
        }
        return "redirect:/centers";
    }
}