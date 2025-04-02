package com.smart_waste_management_system.controller;

import com.smart_waste_management_system.model.CollectionSchedule;
import com.smart_waste_management_system.repository.CollectionScheduleRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/schedules")
public class CollectionScheduleController {

    private CollectionScheduleRepository scheduleRepo;

    @GetMapping
    public String listSchedules(Model model) {
        model.addAttribute("schedules", scheduleRepo.findAll());
        return "schedule/list";
    }

    @GetMapping("/new")
    public String createScheduleForm(Model model) {
        model.addAttribute("schedule", new CollectionSchedule());
        return "schedule/form";
    }

    @PostMapping("/save")
    public String saveSchedule(@Valid @ModelAttribute("schedule") CollectionSchedule schedule, BindingResult result) {
        if(result.hasErrors()){
            return "schedule/form";
        }
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