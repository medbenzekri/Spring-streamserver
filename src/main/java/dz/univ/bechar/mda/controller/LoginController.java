package dz.univ.bechar.mda.controller;

import dz.univ.bechar.mda.entity.User;
import dz.univ.bechar.mda.repository.UserRepository;
import dz.univ.bechar.mda.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;
	@PostMapping("/login")
	public @ResponseBody String Login(@RequestBody User user) {
		String result= loginService.login(user.getUsername(), user.getPassword());

		return result;
	}

}
