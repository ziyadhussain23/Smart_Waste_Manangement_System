package com.smart_waste_management_system.controller;

import com.smart_waste_management_system.model.WasteCategory;
import com.smart_waste_management_system.repository.WasteCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/categories")
public class WasteCategoryController {

    private WasteCategoryRepository categoryRepo;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryRepo.findAll()); // List view [[6]]
        return "category/list";
    }

    @GetMapping("/new")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new WasteCategory());
        return "category/form"; // Form template [[5]]
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute WasteCategory category) {
        if (category.getCategory_id() == 0) {
            categoryRepo.insert(category);
        } else {
            categoryRepo.update(category);
        }
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryRepo.deleteById(id);
        return "redirect:/categories";
    }
}