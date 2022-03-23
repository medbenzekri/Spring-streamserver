package dz.univ.bechar.mda.service;

import dz.univ.bechar.mda.entity.User;
import dz.univ.bechar.mda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository repository;
    public String login(String name , String password){

        User result = repository.findUserByUsernameAndPassword(name,password);
        System.out.println(result);
        if (result!=null)
            return String.format("hello %s", result.getUsername());
        return "user not found ";
    }
}
