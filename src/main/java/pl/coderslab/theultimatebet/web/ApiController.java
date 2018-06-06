package pl.coderslab.theultimatebet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.theultimatebet.service.ApiService;
import pl.coderslab.theultimatebet.service.GroupService;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ApiService apiService;


    @GetMapping(path= "/groups")
    @ResponseBody
    public String getAllGroups() {
        return apiService.getGroups().toString();
    }


    @GetMapping("/teams")
    @ResponseBody
    public String getTeams() {
        return apiService.getTeams().toString();
    }

    @GetMapping(path= "games")
    @ResponseBody
    public String getAllGames() {
        return apiService.getAllGames().toString();
    }

    @GetMapping(path= "/scheduled-games")
    @ResponseBody
    public String getScheduledGames() {
        return apiService.getScheduledGames().toString();
    }

    @GetMapping(path= "/finished-games")
    @ResponseBody
    public String getFinishedGames() {
        return apiService.getFinishedGames().toString();
    }
}
