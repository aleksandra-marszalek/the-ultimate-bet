package pl.coderslab.theultimatebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.theultimatebet.entity.Game;

public interface GameRepository extends JpaRepository <Game, Long> {

    Game findGameById (Long id);
}
