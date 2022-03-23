package dz.univ.bechar.mda.controller;

import dz.univ.bechar.mda.entity.User;
import dz.univ.bechar.mda.service.LoginService;
import dz.univ.bechar.mda.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {
    @Autowired
    private SignupService signupService;
    @PostMapping("/signup")
    public @ResponseBody
    String Signup(@RequestBody User user) {
        String result= signupService.Signup(user);

        return result;
    }
}
