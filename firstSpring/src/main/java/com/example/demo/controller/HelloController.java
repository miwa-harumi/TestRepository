package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/")
	public String firstView(Model model) {
		model.addAttribute("hello", "井出くんは強い");
		return "hello";
	}
}
