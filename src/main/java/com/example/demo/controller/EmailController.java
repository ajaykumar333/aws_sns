package com.example.demo.controller;

import com.example.demo.service.SnsMailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

	@Autowired
    private SnsMailService snsService;
    
    @PostMapping("/send")
    public String sendEmail(@RequestParam String subject, @RequestParam String message) {
        return snsService.publishMail(subject, message);
    }
}
