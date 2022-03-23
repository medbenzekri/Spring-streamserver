package dz.univ.bechar.mda.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany
    private List<User> subscribers ;

  public Role(String name ){
        this.name=name;
    }

    public Role() {

    }

    public Long getId() {
        return id;
    }
}
