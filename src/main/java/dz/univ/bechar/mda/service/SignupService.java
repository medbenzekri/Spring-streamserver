package dz.univ.bechar.mda.service;

import dz.univ.bechar.mda.entity.User;
import dz.univ.bechar.mda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.SchemaOutputResolver;

@Service
public class SignupService {
    @Autowired
    private UserRepository repository;
    public String Signup(User user){
        try {
            repository.save(user);
        }catch (Exception exception){
            System.out.println(exception);
            return "couldn't signup";
        }
        return "signup successfully";



    }
}
