package team13.pulsbes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import team13.pulsbes.dtos.IdPw;
import team13.pulsbes.dtos.LoginDTO;
import team13.pulsbes.exception.WrongCredentialsException;
import team13.pulsbes.serviceimpl.LoginService;
import team13.pulsbes.utils.Constants;

@RequestMapping("/login")
@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = Constants.LOGIN, method = RequestMethod.POST)	
	public LoginDTO login(@RequestBody IdPw idpw)  {
		try {
			return loginService.login(idpw);
		} catch (WrongCredentialsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LoginDTO loginDTO = new LoginDTO();
			return loginDTO;
		}
	}
}
