package pl.coderslab.theultimatebet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.theultimatebet.entity.Bet;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {

    Bet findBetById (Long id);

    List<Bet> findAllByUserId (Long id);

    List<Bet> findAllByResult (String result);

    List<Bet> findAllByUserIdAndResult (Long id, String result);

}
