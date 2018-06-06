package pl.coderslab.theultimatebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.theultimatebet.entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findTeamByApiId (Long id);

    Team findTeamById (Long id);

    List<Team> findAllOrderByFinalStanding ();

    List<Team> findAllOrderBySeeding ();

    List<Team> findAllOrderByStrength ();
}
