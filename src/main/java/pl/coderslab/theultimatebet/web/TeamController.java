package pl.coderslab.theultimatebet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.theultimatebet.CurrentUser;
import pl.coderslab.theultimatebet.entity.Game;
import pl.coderslab.theultimatebet.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class TeamController {

    // TODO: add teams, current standings, possibility to bet on champion

    @Autowired
    TeamService teamService;


    @GetMapping("/{id}/teams")
    public String allTeams(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getUser().getId()==id) {
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("id", id);
            model.addAttribute("allTeams", teamService.findAllOrderBySeeding());
            return "AllTeams";
        } else {
            return "redirect:/";
        }
    }

        @GetMapping("/{id}/teams/{teamId}")
        public String singleTeam(@PathVariable Long id, @PathVariable Long teamId,
                                 @AuthenticationPrincipal CurrentUser currentUser, Model model) {
            if (currentUser.getUser().getId() == id) {
                model.addAttribute("currentUser", currentUser);
                model.addAttribute("id", id);
                model.addAttribute("team", teamService.findById(teamId));
                List<Game> games = new ArrayList<>();
                games.addAll((teamService.findById(teamId).getGamesAsTeam1()));
                games.addAll((teamService.findById(teamId).getGamesAsTeam2()));
                model.addAttribute("games", games);
                return "TeamPage";
            }  else {
                return "redirect:/";
            }
        }
}
