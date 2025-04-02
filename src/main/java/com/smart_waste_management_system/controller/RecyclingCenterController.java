package com.smart_waste_management_system.controller;

import com.smart_waste_management_system.model.RecyclingCenter;
import com.smart_waste_management_system.repository.RecyclingCenterRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/centers")
public class RecyclingCenterController {

    private RecyclingCenterRepository centerRepo;

    @GetMapping
    public String listCenters(Model model) {
        model.addAttribute("centers", centerRepo.findAll());
        return "center/list";
    }

    @GetMapping("/new")
    public String createCenterForm(Model model) {
        model.addAttribute("center", new RecyclingCenter());
        return "center/form";
    }

    @PostMapping("/save")
    public String saveCenter(@Valid @ModelAttribute("center") RecyclingCenter center, BindingResult result) {
        if(result.hasErrors()){
            return "center/form";
        }
        if (center.getCenter_id() == null) {
            centerRepo.insert(center);
        } else {
            centerRepo.update(center);
        }
        return "redirect:/centers";
    }

    @GetMapping("/edit/{id}")
    public String editCenterForm(@PathVariable int id, Model model) {
        centerRepo.findById(id).ifPresent(center -> model.addAttribute("center", center));
        return "center/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCenter(@PathVariable int id) {
        centerRepo.deleteById(id);
        return "redirect:/centers";
    }
}