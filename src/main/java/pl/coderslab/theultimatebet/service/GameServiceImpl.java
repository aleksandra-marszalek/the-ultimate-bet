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


/**
 * Service class related to {@link Game}, containing most of the logic of the games.
 * Have some standard crud methods, main scheduled method used to get the data from the external api and methods used in
 * other services to find specific games.
 */
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

    /**
     * Scheduled for fixed rate method to get games from the external api.
     * Then using {@link GameDto} class, there is new object for each new game created and automatically
     * saved to DB. If game already exists in DB, the method updates all the data related to this game.
     */
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

    /**
     * The method aim is to convert and round up a double value to BigDecimal.
     * (The odds from external api are send as double, and the course is preffered to be in BigDecimal)
     * @param value is a double
     * @param places is how many decimal places are to be round up to
     * @return the rounded value in BigDecimal
     */

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Method used to find all future games of the teams which are marked as favourite by the {@param user}
     * uses favourite repository method to find all favourite teams. Then for each team finds all the games
     * in which the team is present. Then checks the status - if game is not played yet, adds it to the returned game list.
     * @param user
     * @return the list of future games of team marked as favourite by the user
     */

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


    /**
     * Method used to find the games in which there is a big possibility of win by the user.
     * As the courses of teams are based by almost the inverse of the probability to win, the smaller the course is,
     * the bigger the possibility to win is. - TODO: improve the algorithm, so that be more exact
     */
    @Override
    public List<Game> findSuggestedGames () {
        List<Game> scheduledGames = findAllByStatus(0);
        List<Game> suggestedGames = new ArrayList<>();

        for (Game g: scheduledGames) {
            if (g.getCourseForTeam1()<1.6 || g.getCourseForTeam2()<1.6) {
                suggestedGames.add(g);
            }
        }
        return suggestedGames;
    }
}
