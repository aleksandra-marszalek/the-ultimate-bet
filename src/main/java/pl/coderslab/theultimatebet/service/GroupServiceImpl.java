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

@Service
public class GroupServiceImpl implements GroupService {


    @Autowired
    GroupRepository groupRepository;


    ////////////// getting data from api /////////////////

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

//    @Scheduled(fixedRate = 1000*60*60*24)
//    public void assignTeamsToGroupFromApi() {
//
//        //get all groups from db
//
//        //iterate over groups
//
//            //foreach get from api teams
//
//            //check if all teams in db
//
//            //assign teams to group
//
//            //update in db group
//
//
//        String url = "http://localhost:8090/group/";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<GroupDto[]> responseGroups = restTemplate.getForEntity(url, GroupDto[].class);
//        GroupDto[] groups = responseGroups.getBody();
//        for (GroupDto group: groups) {
//            Group g = new Group();
//            if (groupRepository.findGroupById(group.getId()) != null) {
//                g = groupRepository.findGroupById(group.getId());
//            }
//            g.setId(group.getId());
//            g.setName(group.getName());
//            groupRepository.save(g);
//
//        }
//    }

    ////////////// crud //////////////////

    @Override
    public Group findByApiId(Long id) {
        return groupRepository.findGroupByApiId(id);
    }
}
