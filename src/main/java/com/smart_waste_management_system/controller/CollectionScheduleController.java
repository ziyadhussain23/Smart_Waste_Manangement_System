package com.smart_waste_management_system.controller;

import com.smart_waste_management_system.model.CollectionSchedule;
import com.smart_waste_management_system.repository.CollectionScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/schedules")
public class CollectionScheduleController {

    private CollectionScheduleRepository scheduleRepo;

    @GetMapping
    public String listSchedules(Model model) {
        model.addAttribute("schedules", scheduleRepo.findAll());
        return "schedule/list"; // List view [[1]]
    }

    @GetMapping("/new")
    public String createScheduleForm(Model model) {
        model.addAttribute("schedule", new CollectionSchedule());
        return "schedule/form"; // Form template [[5]]
    }

    @PostMapping("/save")
    public String saveSchedule(@ModelAttribute CollectionSchedule schedule) {
        scheduleRepo.save(schedule);
        return "redirect:/schedules";
    }
}