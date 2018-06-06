package pl.coderslab.theultimatebet.service;

import com.oracle.javafx.jmx.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.theultimatebet.entity.Game;
import pl.coderslab.theultimatebet.entity.Group;
import pl.coderslab.theultimatebet.entity.Team;
import pl.coderslab.theultimatebet.repository.GameRepository;
import pl.coderslab.theultimatebet.repository.GroupRepository;
import pl.coderslab.theultimatebet.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GameRepository gameRepository;

    /////////////// to api ///////////////

    public ArrayList<JSONObject> getGroups() {
        return groups;
    }

    private ArrayList<JSONObject> groups = new ArrayList<>();

    @Scheduled(fixedRate = 5000)
    public void regenerateGroups() throws JSONException {
        groups.clear();
        List<Group> allGroups = groupRepository.findAll();
        for (Group g: allGroups) {
            JSONObject oJsonInner = new JSONObject();
            oJsonInner.put("id", g.getId());
            oJsonInner.put("name", g.getName());
            ArrayList<JSONObject> teams = new ArrayList<>();
            for (int i=0; i<g.getTeams().size(); i++) {
                JSONObject team = new JSONObject();
                Team t = g.getTeams().get(i);
                team.put("id", t.getId());
                team.put("name", t.getName());
                team.put("seeding", t.getSeeding());
                team.put("strength", t.getStrength());
//                team.put("group", t.getGroup().getName());
                team.put("placeInGroup", t.getPlaceInGroup());
                team.put("won", t.getWon());
                team.put("lost", t.getLost());
                team.put("pointBalance", t.getPointBalance());
                team.put("finalStanding", t.getFinalStanding());
                teams.add(team);
            }
            oJsonInner.put("teams", teams);
            groups.add(oJsonInner);
        }
    }

    public ArrayList<JSONObject> getTeams() {
        return teams;
    }

    private ArrayList<JSONObject> teams = new ArrayList<>();

    @Scheduled(fixedRate = 5000)
    public void regenerateTeams() throws org.json.JSONException {
        teams.clear();
        List<Team> allTeams = teamRepository.findAll();
        for (Team t: allTeams) {
            JSONObject oJsonInner = new JSONObject();
            oJsonInner.put("id", t.getId());
            oJsonInner.put("name", t.getName());
            oJsonInner.put("seeding", t.getSeeding());
            oJsonInner.put("strength", t.getStrength());
            oJsonInner.put("group_id", t.getGroup().getId());
            oJsonInner.put("placeInGroup", t.getPlaceInGroup());
            oJsonInner.put("won", t.getWon());
            oJsonInner.put("lost", t.getLost());
            oJsonInner.put("pointBalance", t.getPointBalance());
            oJsonInner.put("finalStanding", t.getFinalStanding());
            teams.add(oJsonInner);
        }
    }


    public ArrayList<JSONObject> getAllGames() {
        return games;
    }

    private ArrayList<JSONObject> games = new ArrayList<>();

    @Scheduled(fixedRate = 5000)
    public void regenerateGames() throws org.json.JSONException {
        games.clear();
        List<Game> allGames = gameRepository.findAll();
        newJSONObject(allGames, games);

    }

    public ArrayList<JSONObject> getScheduledGames() {
        return scheduledGames;
    }

    private ArrayList<JSONObject> scheduledGames = new ArrayList<>();

    @Scheduled(fixedRate = 5000)
    public void regenerateScheduled() throws org.json.JSONException {
        scheduledGames.clear();
        List<Game> allGames = gameRepository.findAllByStatus(0);
        newJSONObject(allGames, scheduledGames);

    }


    public ArrayList<JSONObject> getFinishedGames() {
        return finishedGames;
    }

    private ArrayList<JSONObject> finishedGames = new ArrayList<>();

    @Scheduled(fixedRate = 5000)
    public void regenerateFinished() throws org.json.JSONException {
        finishedGames.clear();
        List<Game> allGames = gameRepository.findAllByStatus(1);
        newJSONObject(allGames, finishedGames);

    }

    public void newJSONObject(List<Game> allGames, ArrayList<JSONObject> games) {
        for (Game g: allGames) {
            JSONObject oJsonInner = new JSONObject();
            oJsonInner.put("id", g.getId());
            oJsonInner.put("gameTime", g.getGameTime());
            oJsonInner.put("pointsTeam1", g.getPointsTeam1());
            oJsonInner.put("pointsTeam2", g.getPointsTeam2());
            oJsonInner.put("oddsForTeam1", g.getOddsForTeam1());
            oJsonInner.put("oddsForTeam2", g.getOddsForTeam2());
            oJsonInner.put("status", g.getStatus());
            oJsonInner.put("signature", g.getSignature());
            oJsonInner.put("team1_id", g.getTeam1().getId());
            oJsonInner.put("team2_id", g.getTeam2().getId());
            games.add(oJsonInner);
        }
    }
}
