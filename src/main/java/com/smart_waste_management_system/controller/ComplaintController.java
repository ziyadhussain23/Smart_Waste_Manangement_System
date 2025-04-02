package com.smart_waste_management_system.controller;

import com.smart_waste_management_system.model.Complaint;
import com.smart_waste_management_system.repository.ComplaintRepository;
import com.smart_waste_management_system.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/complaints")
public class ComplaintController {

    private ComplaintRepository complaintRepo;
    private UserRepository userRepo;

    @GetMapping
    public String listComplaints(Model model) {
        model.addAttribute("complaints", complaintRepo.findAll());
        return "complaint/list";
    }

    @GetMapping("/new")
    public String submitComplaintForm(Model model) {
        model.addAttribute("complaint", new Complaint());
        model.addAttribute("users", userRepo.findAll());
        return "complaint/form";
    }

    @PostMapping("/save")
    public String saveComplaint(@Valid @ModelAttribute("complaint") Complaint complaint, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("users", userRepo.findAll());
            return "complaint/form";
        }
        complaint.setStatus("Pending");
        if (complaint.getComplaint_id() == null) {
            complaintRepo.insert(complaint);
        } else {
            complaintRepo.update(complaint);
        }
        return "redirect:/complaints";
    }

    @GetMapping("/resolve/{id}")
    public String resolveComplaint(@PathVariable int id) {
        complaintRepo.findById(id).ifPresent(complaint -> {
            complaint.setStatus("Resolved");
            complaintRepo.update(complaint);
        });
        return "redirect:/complaints";
    }
}