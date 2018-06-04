package pl.coderslab.theultimatebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.theultimatebet.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findTeamByApiId (Long id);

    Team findTeamById (Long id);
}
