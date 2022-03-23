package dz.univ.bechar.mda.repository;

import dz.univ.bechar.mda.entity.Membership;
import dz.univ.bechar.mda.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends CrudRepository<Membership, Long> {
}
