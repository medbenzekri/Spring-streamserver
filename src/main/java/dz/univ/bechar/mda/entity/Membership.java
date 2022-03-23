package dz.univ.bechar.mda.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Membership {
    @Id
    @GeneratedValue
    Long Id ;
    String name;
    @OneToMany
    List<User> subscribers ;
    public Membership(String name){this.name=name;}

    public Membership() {

    }
}
