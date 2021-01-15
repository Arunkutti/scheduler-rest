package com.iq.code.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
	

	@GetMapping("/")
	public String index() {
		return "Up";
	}

}
