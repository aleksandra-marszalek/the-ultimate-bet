package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.theultimatebet.dto.TeamDto;
import pl.coderslab.theultimatebet.entity.Team;
import pl.coderslab.theultimatebet.repository.GroupRepository;
import pl.coderslab.theultimatebet.repository.TeamRepository;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GroupRepository groupRepository;

    ///////// from api /////////////

    @Scheduled(fixedRate = 1000*10)
    public void getTeamsFromApi() {
        String url = "http://localhost:8090/team/";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TeamDto[]> responseTeams = restTemplate.getForEntity(url, TeamDto[].class);
        TeamDto[] teams = responseTeams.getBody();
        for (TeamDto team: teams) {
            Team t = new Team();
            if (findByApiId(team.getApiId()) != null) {
                t = findByApiId(team.getApiId());
            }
            t.setApiId(team.getApiId());
            t.setName(team.getName());
            t.setFinalStanding(team.getFinalStanding());
            t.setLoserWinerSignature(team.getLoserWinerSignature());
            t.setLost(team.getLost());
            t.setPlaceInGroup(team.getPlaceInGroup());
            t.setPointBalance(team.getPointBalance());
            t.setSeeding(team.getSeeding());
            t.setStrength(team.getStrength());
            t.setWon(team.getWon());
            t.setGroup(groupRepository.findGroupByApiId(team.getGroup_id()));
            teamRepository.save(t);
        }
    }


    /////////////// crud /////////////////

    @Override
    public Team findByApiId(Long id) {
        return teamRepository.findTeamByApiId(id);
    }

    @Override
    public Team findById(Long id) {
        return teamRepository.findTeamById(id);
    }



//    @Override
    public List<Team> findAllOrderByStandings() {
        return teamRepository.findAllOrderByFinalStanding();
    }

    @Override
    public List<Team> findAllOrderBySeeding() {
        return teamRepository.findAllOrderBySeeding();
    }


}
