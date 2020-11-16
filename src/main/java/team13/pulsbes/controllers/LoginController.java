package team13.pulsbes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team13.pulsbes.dtos.IdPw;
import team13.pulsbes.dtos.LoginDTO;
import team13.pulsbes.exception.WrongCredentialsException;
import team13.pulsbes.serviceimpl.LoginService;
import team13.pulsbes.utils.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/login")
@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = Constants.LOGIN, method = RequestMethod.POST)	
	public LoginDTO login(@RequestBody IdPw idpw, HttpServletResponse response)  {
		try {
			LoginDTO log = loginService.login(idpw);
			if(!log.getId().equals("-1")){
				Cookie cookieUsername = new Cookie("username", log.getEmail());
				Cookie cookieId = new Cookie("id", log.getId());
				cookieUsername.setPath("/");
				cookieId.setPath("/");
				cookieUsername.setMaxAge(3600*7);
				cookieId.setMaxAge(3600*7);
				//add cookie to response
				response.addCookie(cookieUsername);
				response.addCookie(cookieId);
			}
			return log;
		} catch (WrongCredentialsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LoginDTO loginDTO = new LoginDTO();
			return loginDTO;
		}
	}

	@GetMapping("/cookie")
	public String readCookie(@CookieValue(value = "username") String username) {
		return "Hey! My username is " + username;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout(HttpServletResponse response){
		// create a cookie
		Cookie cookieUsername = new Cookie("username", null);
		Cookie cookieId = new Cookie("id", null);
		cookieUsername.setPath("/");
		cookieId.setPath("/");
		cookieUsername.setMaxAge(0);
		cookieId.setMaxAge(0);
		//add cookie to response
		response.addCookie(cookieId);
		response.addCookie(cookieUsername);
	}
}
