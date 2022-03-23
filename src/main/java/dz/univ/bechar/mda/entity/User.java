package dz.univ.bechar.mda.entity;

import javax.persistence.*;

@Entity
public class User {
    @Id
    private String username;
    private String password;
    private String email;
    @ManyToOne
    private Role role;
    @ManyToOne
    private Membership membership;

    public  User(String username, String password,String email,Role role){
        this.password=password;
        this.username = username;
        this.email=email;
        this.role= role ;
    }

    public User() {}

    public User(String username,String password){
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public Membership getMembership() {
        return membership;
    }
}
