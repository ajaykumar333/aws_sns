package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.SnsSmsService;

@RestController
@RequestMapping("/api/sms")
public class SMSController {
	
	@Autowired
	private SnsSmsService snsSmsService;
	
	@PostMapping("/send")
	public String sendSms(@RequestParam String number, @RequestParam String message) {
		
		try {
			return snsSmsService.sendSms(number, message);
		} catch (Exception e) {
			throw new RuntimeException("Error Occured");
		}
	}

}
