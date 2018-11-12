package com.uproject.generic.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Redirect user to swagger documentation
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 *
 */
@Controller
@RequestMapping("/")
public class RootController {

	@GetMapping("")
	public String get() {
		return "redirect:/swagger-ui.html";
	}
}
