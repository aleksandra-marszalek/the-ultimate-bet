package pl.coderslab.theultimatebet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.theultimatebet.CurrentUser;
import pl.coderslab.theultimatebet.entity.Favourite;
import pl.coderslab.theultimatebet.entity.Game;
import pl.coderslab.theultimatebet.entity.Team;
import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.service.FavouriteService;
import pl.coderslab.theultimatebet.service.GameService;
import pl.coderslab.theultimatebet.service.TeamService;
import pl.coderslab.theultimatebet.service.UserService;

import java.util.List;
/**
 * Controller responsible for providing all the data to the homepage and connecting the services with the views.
 */
@Controller
public class HelloController {

    @Autowired
    UserService userService;

    @Autowired
    FavouriteService favouriteService;

    @Autowired
    GameService gameService;

    /**
     * GET for showing all the {@link pl.coderslab.theultimatebet.entity.Game}
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @return the view with all the games or redirects to home page if no authorities
     */
    /**
     * GET for the view of homepage, which shows scheduled {@link Game} for the {@link User} {@link Team},
     * and suggested {@link Game} if the {@link User} is logged and just the plain homepage with login and register
     * possibilites.
     * @param currentUser to authenticate user
     * @param model to send the data about favourite teams
     * @return home view for user logged or plain home view for not logged
     */
    @GetMapping("/")
    public String home (@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        try {
            Long id = currentUser.getUser().getId();
            if (currentUser.getUser().getId()!=null) {
                model.addAttribute("currentUser", currentUser);
                model.addAttribute("id", id);


                List<Game> games = gameService.findGamesByUserFavourite(currentUser.getUser());
                if (games.size()==0) {
                    model.addAttribute("info", "You have no favourite teams or your teams don't have any planned games :(");
                }
                model.addAttribute("games", games);


                List<Game> suggestedGames = gameService.findSuggestedGames();
                if (suggestedGames.size()==0) {
                    model.addAttribute("info2", "There are no suggested games at the moment... ");
                }
                model.addAttribute("suggested", suggestedGames);

                return "homeLogged";

            }
        } catch (Exception e) {
            return "home";
        }
        return "home";
    }

    /**
     * POST for logging the user out
     * @return the home for not logged user
     */
    @PostMapping("/logout")
    public String homeLogout () {return "home";}

//    @GetMapping("/helloUser")
//    @ResponseBody
//    public String helloUser () {
//        return "Hello authenticated user";
//    }
//
//    @ResponseBody
//    @GetMapping("/addUser")
//    public String addUser() {
//        User user = new User();
//        user.setUsername("user");
//        user.setPassword("user123");
//        userService.saveUser(user);
//        return "added User";
//    }
//
//    @ResponseBody
//    @GetMapping("/addAdmin")
//    public String addAdmin() {
//        User user = new User();
//        user.setUsername("admin");
//        user.setPassword("admin123");
//        userService.saveUser(user);
//        return "added admin";
//    }
}
