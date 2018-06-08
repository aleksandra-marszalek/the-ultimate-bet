package pl.coderslab.theultimatebet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.theultimatebet.CurrentUser;
import pl.coderslab.theultimatebet.entity.*;
import pl.coderslab.theultimatebet.service.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Controller responsible for providing all the data related to {@link Team} and connecting the services with the views.
 */
@Controller
@RequestMapping("/user")
public class
TeamController {

    // TODO: add current standings, (groups and quarterfinals, semifinals standings), possibility to bet on champion

    @Autowired
    TeamService teamService;

    @Autowired
    FavouriteService favouriteService;

    @Autowired
    UserService userService;

    /**
     * GET for showing all the {@link Team} ordered by seeding
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @return the view with all the teams, ordered by seeding.
     */
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

    /**
     * GET for showing all the {@link Team} ordered by final standings
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @return the view with all the teams, ordered by final standings - if the finals has not been played,
     * they are ordered by the id and the right info is displayed
     */
    @GetMapping("/{id}/teams/finalStandings")
    public String finalStandings(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getUser().getId()==id) {
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("id", id);
            List<Team> allTeams = teamService.findAllOrderByStandings();
            model.addAttribute("allTeams", allTeams);
            if (allTeams.get(0).getFinalStanding()==0) {
                model.addAttribute("info", "the finals has not been played yet");
            } else {
                model.addAttribute("info", "the final standings are here");
            }

            return "FinalStandings";
        } else {
            return "redirect:/";
        }
    }

    /**
     * GET for showing the specific {@link Team}
     * @param teamId id of the {@link Team}
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @return the view with the specific team
     **/
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

    /**
     * GET for adding the specific {@link Team} to {@link Favourite}
     * @param teamId id of the {@link Team}
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @return the view with the confirmation of the adding the team to favourite
     **/
    @GetMapping("/{id}/teams/addToFavourite/{teamId}/")
    public String addToFavourite (@PathVariable Long id, @PathVariable Long teamId,
                             @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser.getUser().getId() == id) {
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("id", id);
            model.addAttribute("team", teamService.findById(teamId));
            return "AddToFavourite";
        }  else {
            return "redirect:/";
        }
    }

    /**
     * POST for adding the specific {@link Team} to {@link Favourite}
     * @param teamId id of the {@link Team}
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param agree to confirm adding to favourite
     * @return {@link Favourite} list if add works and to teams if doesn't
     */
    @PostMapping("/{id}/teams/addToFavourite/{teamId}/")
    public String addToFavourite (@PathVariable Long id, @PathVariable Long teamId, @RequestParam String agree, @AuthenticationPrincipal CurrentUser currentUser) {
        if (agree.equals("yes")) {
            Team team = teamService.findById(teamId);
            User user = userService.findById(id);
            Favourite favourite = favouriteService.findFavouriteByUser(user);
            List<Team> teams = favourite.getTeams();
            teams.add(team);
            favourite.setTeams(teams);
            favourite.setUser(user);
            favouriteService.save(favourite);
            return "redirect:/user/"+id+"/teams/favourites";
        }
        return "redirect:/user/"+id+"/teams/";
    }

    /**
     * GET for showing all the {@link Favourite} {@link Team} ordered by seeding
     * @param id is the id of the {@link User}
     * @param logedUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @return the view with all the favourite teams of the user
     */
    @GetMapping("/{id}/teams/favourites")
    public String allFavouritesTeams(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser logedUser) {
        User currentUser = userService.findById(logedUser.getUser().getId());
        if (currentUser.getId()==id) {
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("id", id);
            model.addAttribute("allTeams", favouriteService.findFavouriteByUser(currentUser).getTeams());
            return "FavouriteTeams";
        } else {
            return "redirect:/";
        }
    }

}
