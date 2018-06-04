package pl.coderslab.theultimatebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.theultimatebet.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findGroupById (Long id);
}
