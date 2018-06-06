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

@Controller
@RequestMapping("/user")
public class
TeamController {

    // TODO: add teams, current standings, possibility to bet on champion

    @Autowired
    TeamService teamService;

    @Autowired
    FavouriteService favouriteService;

    @Autowired
    UserService userService;


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
            return "/";
//            return "redirect:/user"+id+"/teams/favourites";
//            User user = userService.findById(id);
//            Wallet wallet = walletService.findWalletByUser(user);
//            BigDecimal amountDecimal = BigDecimal.valueOf(amount);
//            wallet.setBalance(wallet.getBalance().add(amountDecimal));
//            walletService.save(wallet);
//
//            Operation operation = new Operation();
//            operation.setCreated(LocalDateTime.now());
//            operation.setAmount(amountDecimal);
//            operation.setWallet(wallet);
//            operation.setTitle("add money");
//            operationService.save(operation);
        }
        return "redirect:/user/"+id+"/teams/";
    }

    @GetMapping("/{id}/teams/favourites")
    public String allFavouritesTeams(@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getUser().getId()==id) {
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("id", id);
            model.addAttribute("allTeams", favouriteService.findFavouriteByUser(currentUser.getUser()).getTeams());
            return "FavouriteTeams";
        } else {
            return "redirect:/";
        }
    }

}
