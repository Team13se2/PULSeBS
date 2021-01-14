package team13.pulsbes.controllers;

import java.util.logging.Logger;

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

	Logger loglog = Logger.getLogger("LoginController");
	
	@PostMapping(value = Constants.LOGIN)	
	public LoginDTO login(@RequestBody IdPw idpw, HttpServletResponse response)  {
		try {
			LoginDTO log = loginService.login(idpw);
			if(!log.getId().equals("-1")){
				Cookie cookieUsername = new Cookie("username", log.getEmail());
				cookieUsername.setSecure(true);
				cookieUsername.setHttpOnly(true);
				Cookie cookieId = new Cookie("id", log.getId());
				Cookie cookieType = new Cookie("type", log.getRole());
				cookieId.setSecure(true);
				cookieId.setHttpOnly(true);
				cookieType.setSecure(true);
				cookieType.setHttpOnly(true);
				cookieUsername.setPath("/");
				cookieType.setPath("/");
				cookieId.setPath("/");
				cookieUsername.setMaxAge(3600*7);
				cookieType.setMaxAge(3600*7);
				cookieId.setMaxAge(3600*7);
				//add cookie to response
				response.addCookie(cookieUsername);
				response.addCookie(cookieId);
				response.addCookie(cookieType);
			}
			return log;
		} catch (WrongCredentialsException e) {
			// TODO Auto-generated catch block
			loglog.throwing(this.getClass().getName(), "login", e);
			//e.printStackTrace();
			//LoginDTO loginDTO = new LoginDTO();
			return (new LoginDTO());
		}
	}

	@GetMapping("/cookie")
	public String readCookie(@CookieValue(value = "username") String username) {
		return "Hey! My username is " + username;
	}

	@PostMapping(value = "/logout")
	public void logout(HttpServletResponse response){
		// create a cookie
		Cookie cookieUsername = new Cookie("username", null);
		cookieUsername.setSecure(true);
		cookieUsername.setHttpOnly(true);
		Cookie cookieId = new Cookie("id", null);
		Cookie cookieType = new Cookie("type", null);
		cookieId.setSecure(true);
		cookieId.setHttpOnly(true);
		cookieType.setSecure(true);
		cookieType.setHttpOnly(true);
		cookieUsername.setPath("/");
		cookieId.setPath("/");
		cookieUsername.setMaxAge(0);
		cookieId.setMaxAge(0);
		cookieType.setPath("/");
		cookieType.setMaxAge(0);
		//add cookie to response
		response.addCookie(cookieId);
		response.addCookie(cookieUsername);
		response.addCookie(cookieType);
	}
}
