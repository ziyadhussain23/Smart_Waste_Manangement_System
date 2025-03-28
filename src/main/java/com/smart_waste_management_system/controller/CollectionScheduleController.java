package com.smart_waste_management_system.controller;

import com.smart_waste_management_system.model.CollectionSchedule;
import com.smart_waste_management_system.repository.CollectionScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        if (schedule.getSchedule_id() == null) {
            scheduleRepo.insert(schedule);
        } else {
            scheduleRepo.update(schedule);
        }
        return "redirect:/schedules";
    }

    @GetMapping("/complete/{id}")
    public String completeSchedule(@PathVariable int id) {
        scheduleRepo.findById(id).ifPresent(schedule -> {
            schedule.setStatus("Completed");
            scheduleRepo.update(schedule);
        });
        return "redirect:/schedules";
    }
}