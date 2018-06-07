package pl.coderslab.theultimatebet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.theultimatebet.dto.GroupDto;
import pl.coderslab.theultimatebet.entity.Group;
import pl.coderslab.theultimatebet.repository.GroupRepository;

import java.util.concurrent.TimeUnit;

/**
 * Service class related to {@link Group}, containing most of the logic of the groups.
 * Has method to find group by api id nad main scheduled method used to get the data from the external api.
 */
@Service
public class GroupServiceImpl implements GroupService {


    @Autowired
    GroupRepository groupRepository;


    ////////////// getting data from api /////////////////


    /**
     * Scheduled for fixed rate method to get groups from the external api.
     * Then using {@link GroupDto} class, there is new object for each new group created and automatically
     * saved to DB. If group already exists in DB, the method updates all the data related to this game.
     */
    @Scheduled(fixedRate = 1000*60*60*24)
    public void getGroupsFromApi() {
        String url = "http://localhost:8090/group/";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GroupDto[]> responseGroups = restTemplate.getForEntity(url, GroupDto[].class);
        GroupDto[] groups = responseGroups.getBody();
        for (GroupDto group: groups) {
            Group g = new Group();
            if (groupRepository.findGroupByApiId(group.getApiId()) != null) {
                g = groupRepository.findGroupByApiId(group.getApiId());
            }
            g.setApiId(group.getApiId());
            g.setName(group.getName());
            groupRepository.save(g);

        }
    }


    ////////////// crud //////////////////

    @Override
    public Group findByApiId(Long id) {
        return groupRepository.findGroupByApiId(id);
    }
}
