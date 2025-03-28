package com.smart_waste_management_system.controller;

import com.smart_waste_management_system.model.Complaint;
import com.smart_waste_management_system.repository.ComplaintRepository;
import com.smart_waste_management_system.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "complaint/list"; // List view [[1]]
    }

    @GetMapping("/new")
    public String submitComplaintForm(Model model) {
        model.addAttribute("complaint", new Complaint());
        model.addAttribute("users", userRepo.findAll()); // Dropdown for users [[5]]
        return "complaint/form";
    }

    @PostMapping("/save")
    public String saveComplaint(@ModelAttribute Complaint complaint) {
        complaint.setStatus("Pending");
        complaintRepo.save(complaint);
        return "redirect:/complaints";
    }

    @GetMapping("/resolve/{id}")
    public String resolveComplaint(@PathVariable int id) {
        Complaint complaint = complaintRepo.findById(id).orElse(null);
        if (complaint != null) {
            complaint.setStatus("Resolved");
            complaintRepo.save(complaint);
        }
        return "redirect:/complaints";
    }
}