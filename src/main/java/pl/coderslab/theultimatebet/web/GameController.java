package pl.coderslab.theultimatebet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.theultimatebet.CurrentUser;
import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.service.GameService;
import pl.coderslab.theultimatebet.service.OperationService;
import pl.coderslab.theultimatebet.service.UserService;
import pl.coderslab.theultimatebet.service.WalletService;

/**
 * Controller responsible for providing all the data related to {@link pl.coderslab.theultimatebet.entity.Game} and connecting the services with the views.
 */
@Controller
@RequestMapping("/user")
public class GameController {

    @Autowired
    WalletService walletService;

    @Autowired
    UserService userService;

    @Autowired
    OperationService operationService;

    @Autowired
    GameService gameService;


    /**
     * GET for showing all the {@link pl.coderslab.theultimatebet.entity.Game}
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @return the view with all the games or redirects to home page if no authorities
     */
    @GetMapping("/{id}/allGames")
    public String allGames (@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getUser().getId()==id) {
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("plannedGames", gameService.findAllByStatus(0));
            model.addAttribute("finishedGames", gameService.findAllByStatus(1));
            model.addAttribute("id", id);
            return "AllGames";
        } else {
            return "redirect:/";
        }
    }

    /**
     * GET for showing all the scheduled {@link pl.coderslab.theultimatebet.entity.Game}
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @return the view with all the scheduled games or redirects to home page if no authorities
     */
    @GetMapping("/{id}/allGames/scheduled")
    public String allGamesScheduled (@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getUser().getId()==id) {
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("plannedGames", gameService.findAllByStatus(0));
            model.addAttribute("id", id);
            return "AllGamesScheduled";
        } else {
            return "redirect:/";
        }
    }

    /**
     * GET for showing all the finished {@link pl.coderslab.theultimatebet.entity.Game}
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @return the view with all the finished games or redirects to home page if no authorities
     */
    @GetMapping("/{id}/allGames/finished")
    public String allGamesFinished (@PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getUser().getId()==id) {
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("finishedGames", gameService.findAllByStatus(1));
            model.addAttribute("id", id);
            return "AllGamesFinished";
        } else {
            return "redirect:/";
        }
    }


}
