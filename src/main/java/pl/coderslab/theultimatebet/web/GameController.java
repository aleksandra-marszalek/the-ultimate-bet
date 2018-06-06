package pl.coderslab.theultimatebet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.theultimatebet.CurrentUser;
import pl.coderslab.theultimatebet.service.GameService;
import pl.coderslab.theultimatebet.service.OperationService;
import pl.coderslab.theultimatebet.service.UserService;
import pl.coderslab.theultimatebet.service.WalletService;

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
