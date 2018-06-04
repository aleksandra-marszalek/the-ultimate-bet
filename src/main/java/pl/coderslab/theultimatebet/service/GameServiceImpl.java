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

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TeamRepository teamRepository;


    /////////// getting from API /////////////////

    @Scheduled(fixedRate = 1000*60)
    public void getGamesFromApi() {
        String url = "http://localhost:8090/game/";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GameDto[]> responseGames = restTemplate.getForEntity(url, GameDto[].class);
        GameDto[] games = responseGames.getBody();
        for (GameDto game: games) {
            Game g;
            if (findById(game.getId()) != null) {
                g = findById(game.getId());
            } else {
                g = new Game();
            }
            g.setId(game.getId());
            g.setGameTime(game.getGameTime());
            g.setOddsForTeam1(game.getOddsForTeam1());
            g.setOddsForTeam2(game.getOddsForTeam2());
            g.setPointsTeam1(game.getPointsTeam1());
            g.setPointsTeam2(game.getPointsTeam2());
            g.setSignature(game.getSignature());
            g.setStatus(game.getStatus());
            g.setTeam1(teamRepository.findTeamById(game.getTeam1Id()));
            g.setTeam2(teamRepository.findTeamById(game.getTeam2Id()));
            gameRepository.save(g);
        }
    }


    //////////// crud ////////////////

    @Override
    public Game findById(Long id) {
        return gameRepository.findGameById(id);
    }
}
