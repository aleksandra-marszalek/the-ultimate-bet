package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.theultimatebet.dto.GameDto;
import pl.coderslab.theultimatebet.dto.TeamDto;
import pl.coderslab.theultimatebet.entity.Game;
import pl.coderslab.theultimatebet.entity.Team;
import pl.coderslab.theultimatebet.repository.GameRepository;
import pl.coderslab.theultimatebet.repository.TeamRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;


    @Autowired
    TeamRepository teamRepository;


    /////////// getting from API /////////////////

    @Scheduled(fixedRate = 1000*10)
    public void getGamesFromApi() {
        String url = "http://localhost:8090/game/";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GameDto[]> responseGames = restTemplate.getForEntity(url, GameDto[].class);
        GameDto[] games = responseGames.getBody();
        for (GameDto game: games) {
            Game g;
            if (findByApiId(game.getApiId()) != null) {
                g = findByApiId(game.getApiId());
            } else {
                g = new Game();
            }
            g.setApiId(game.getApiId());
            g.setGameTime(game.getGameTime());
            g.setOddsForTeam1(game.getOddsForTeam1());
            g.setOddsForTeam2(game.getOddsForTeam2());
            g.setPointsTeam1(game.getPointsTeam1());
            g.setPointsTeam2(game.getPointsTeam2());
            g.setSignature(game.getSignature());
            g.setStatus(game.getStatus());
            g.setCourseForTeam1(round((1.0/g.getOddsForTeam1())*1.10, 2));
            g.setCourseForTeam2(round((1.0/g.getOddsForTeam2())*1.10, 2));
            g.setTeam1(teamRepository.findTeamByApiId(game.getTeam1Id()));
            g.setTeam2(teamRepository.findTeamByApiId(game.getTeam2Id()));
            gameRepository.save(g);
        }
    }


    //////////// crud ////////////////

    @Override
    public Game findById (Long id) { return gameRepository.findGameById(id); }

    @Override
    public Game findByApiId(Long id) {
        return gameRepository.findGameByApiId(id);
    }

    @Override
    public List<Game> findAllByStatus(int status) {
        return gameRepository.findAllByStatus(status);
    }


    /////////// other ///////////////

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
