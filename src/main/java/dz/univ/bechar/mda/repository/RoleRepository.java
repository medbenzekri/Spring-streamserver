package dz.univ.bechar.mda.repository;

import dz.univ.bechar.mda.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {


}
