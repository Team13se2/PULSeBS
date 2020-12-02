package team13.pulsbes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
	@GetMapping(value = "/")
	public String index() {
		return login();
	}
	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}
}
