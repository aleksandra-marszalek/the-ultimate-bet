package pl.coderslab.theultimatebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.theultimatebet.entity.Game;

import java.util.List;

public interface GameRepository extends JpaRepository <Game, Long> {

    Game findGameByApiId (Long id);

    Game findGameById (Long id);

    List<Game> findAllByStatusOrderByGameTimeDesc(int status);
}
