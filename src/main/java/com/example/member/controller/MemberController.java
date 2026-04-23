package com.example.member.controller;

import com.example.member.dto.MemberDTO;
import com.example.member.model.Member;
import com.example.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public String viewMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "memberList";
    }

    @PostMapping("/add")
    public String addMember(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email,
            Model model) {
        try {
            if (id == null || id.trim().isEmpty() || name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty()) {
                model.addAttribute("error", "All fields (ID, Name, and Email) are required.");
                return "error";
            }
            if (!isValidInput(id)) {
                model.addAttribute("error", "Invalid ID format. Only alphanumeric and special characters are allowed.");
                return "error";
            }
            
            // Create a new member and add to the service
            Member newMember = new Member();
            newMember.setId(id); // Now directly set as String
            newMember.setName(name);
            newMember.setEmail(email);
            memberService.registerMember(newMember);
            return "redirect:/members";
        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred: " + e.getMessage());
            return "error";
        }
    }

    private boolean isValidInput(String str) {
        return str != null && str.matches("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]*$");
    }
}
