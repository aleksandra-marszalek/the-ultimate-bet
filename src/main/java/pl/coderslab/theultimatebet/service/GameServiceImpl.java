package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.theultimatebet.dto.GameDto;
import pl.coderslab.theultimatebet.entity.Favourite;
import pl.coderslab.theultimatebet.entity.Game;
import pl.coderslab.theultimatebet.entity.Team;
import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.repository.GameRepository;
import pl.coderslab.theultimatebet.repository.TeamRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {



    @Autowired
    GameRepository gameRepository;


    @Autowired
    TeamRepository teamRepository;

    @Autowired
    FavouriteService favouriteService;

    @Autowired
    TeamService teamService;


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
        return gameRepository.findAllByStatusOrderByGameTimeDesc(status);
    }


    /////////// other ///////////////

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public List<Game> findGamesByUserFavourite (User user) {
        Favourite favourite = favouriteService.findFavouriteByUser(user);
        List<Team> teams = favourite.getTeams();
        List<Game> games = new ArrayList<>();

        for (Team t : teams) {
            List<Game> gamesAs1 = teamService.findById(t.getId()).getGamesAsTeam1();
            List<Game> gamesAs2 = teamService.findById(t.getId()).getGamesAsTeam2();
            for (Game g: gamesAs1) {
                if (g.getStatus()==0) {
                    games.add(g);
                }
            }
            for (Game g: gamesAs2) {
                if (g.getStatus()==0) {
                    games.add(g);
                }
            }
        }
        return games;
    }

    @Override
    public List<Game> findSuggestedGames () {
        List<Game> scheduledGames = findAllByStatus(0);
        List<Game> suggestedGames = new ArrayList<>();

        for (Game g: scheduledGames) {
            if (g.getCourseForTeam1()<1.5 || g.getCourseForTeam2()<1.5) {
                suggestedGames.add(g);
            }
        }
        return suggestedGames;
    }
}
