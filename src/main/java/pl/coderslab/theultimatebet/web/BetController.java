package pl.coderslab.theultimatebet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.theultimatebet.CurrentUser;
import pl.coderslab.theultimatebet.entity.*;
import pl.coderslab.theultimatebet.service.*;
import pl.coderslab.theultimatebet.validationGroups.ValidationUser;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class BetController {

        @Autowired
        WalletService walletService;

        @Autowired
        UserService userService;

        @Autowired
        OperationService operationService;

        @Autowired
        GameService gameService;

        @Autowired
        BetService betService;

        @GetMapping("/{id}/bets")
        public String allBets(@PathVariable Long id, @AuthenticationPrincipal CurrentUser customUser, Model model) {
            if (customUser.getUser().getId() == id) {
                model.addAttribute("currentUser", customUser);
                List<Bet> allBets = betService.findAllByUserId(id);
                model.addAttribute("allBets", allBets);
                return "AllBets";
            } else {
                return "redirect:/";
            }
        }


        @GetMapping("/{id}/bets/active")
        public String allBetsActive (@PathVariable Long id, @AuthenticationPrincipal CurrentUser customUser, Model model) {
            if (customUser.getUser().getId() == id) {
                model.addAttribute("currentUser", customUser);
                List<Bet> allBets = betService.findAllByUserIdAndResult(id, null);
                model.addAttribute("allBets", allBets);
                return "AllBetsActive";
            } else {
                return "redirect:/";
            }
        }


        @GetMapping("/{id}/bets/finished")
        public String allBetsFinished (@PathVariable Long id, @AuthenticationPrincipal CurrentUser customUser, Model model) {
            if (customUser.getUser().getId() == id) {
                model.addAttribute("currentUser", customUser);
                List<Bet> all = betService.findAllByUserId(id);
                List<Bet> allBets = new ArrayList<>();
                for (Bet b: all) {
                    if (b.getResult()!=null) {
                        allBets.add(b);
                    }
                }
                model.addAttribute("allBets", allBets);
                return "AllBetsFinished";
            } else {
                return "redirect:/";
            }
        }


        @GetMapping("/{id}/bets/{betId}")
        public String singleBet (@PathVariable Long id, @PathVariable Long betId, @AuthenticationPrincipal CurrentUser customUser, Model model) {
            if ((customUser.getUser().getId() == id) && (betService.findById(betId).getUser().getId()==id)) {
                model.addAttribute("currentUser", customUser);
                Bet bet = betService.findById(betId);
                model.addAttribute("bet", bet);
                if (bet.getResult()==null) {
                   return "SingleBetPageActive";
                } else {
                    return "SingleBetPage";
                }
            } else {
                return "redirect:/";
            }
        }

    @GetMapping("/{id}/bets/{betId}/cancel")
    public String cancelBet (@PathVariable Long id, @PathVariable Long betId, @AuthenticationPrincipal CurrentUser customUser, Model model) {
        if ((customUser.getUser().getId() == id) && (betService.findById(betId).getUser().getId()==id)) {
            model.addAttribute("currentUser", customUser);
            Bet bet = betService.findById(betId);
            model.addAttribute("bet", bet);
            model.addAttribute("id", id);
            return "cancelBet";
        } else {
            return "redirect:/";
        }
    }

    @Transactional
    @PostMapping("/{id}/bets/{betId}/cancel")
    public String cancelBet (@PathVariable Long id, @AuthenticationPrincipal CurrentUser customUser,
                             @PathVariable Long betId, @RequestParam String agree, Model model) {
        model.addAttribute("currentUser", customUser);
        model.addAttribute("id", id);
        if (agree.equals("yes")) {
            Bet bet = betService.findById(betId);
            if (bet.getGame().getStatus()==1) {
                model.addAttribute("info", "This game has already finished. You cannot cancel this bet anymore.");
                return "error";
            }
            else {
                Wallet wallet = walletService.findWalletByUser(bet.getUser());
                wallet.setBalance(wallet.getBalance().add(bet.getAmount().multiply(BigDecimal.valueOf(0.9))));
                Operation operation = new Operation();
                operation.setTitle("cancel bet nr " + bet.getId() + " - return money");
                operation.setWallet(wallet);
                operation.setAmount(bet.getAmount().multiply(BigDecimal.valueOf(0.9)));
                operation.setCreated(LocalDateTime.now());
                operationService.save(operation);
                walletService.save(wallet);
                betService.delete(bet);
            }
        }
        return "redirect:/user/"+id+"/wallet";
    }


        @GetMapping("/{id}/bets/{gameId}/addBet")
        public String addBet (@PathVariable Long id, @PathVariable Long gameId, @AuthenticationPrincipal CurrentUser customUser, Model model) {
            if (customUser.getUser().getId() == id) {
                model.addAttribute("currentUser", customUser);
                model.addAttribute("id", id);
                Game game = gameService.findById(gameId);
                List<Team> teams = new ArrayList<>();
                teams.add(game.getTeam1());
                teams.add(game.getTeam2());
                model.addAttribute("teams", teams);
                model.addAttribute("game", game);
                Bet bet = new Bet();
                bet.setGame(game);
                bet.setUser(customUser.getUser());
                model.addAttribute("bet", bet);
                return "addBet";
            } else {
                return "redirect:/";
            }
        }

        @Transactional
        @PostMapping("/{id}/bets/{gameId}/addBet")
        public String addBet(@Valid @ModelAttribute Bet bet, BindingResult result,
                             @PathVariable Long id, @PathVariable Long gameId, Model model,
                             @AuthenticationPrincipal CurrentUser customUser) {
            model.addAttribute("currentUser", customUser);
            model.addAttribute("id", id);
            model.addAttribute("bet", bet);
            model.addAttribute("gameId", gameId);
            if (result.hasErrors()) {
                return "addBet";
            }
            if (gameService.findById(gameId).getStatus()==1) {
                model.addAttribute("info", "This game has already finished. You cannot place a bet anymore. " +
                        "Use menu to place another bet.");
                return "error";
            }
            if (customUser.getUser().getWallet().getBalance().compareTo(bet.getAmount())==-1) {
                model.addAttribute("info2", "You don't have enough money. " +
                        "Go back to bet to select another amount or add some money to your account ASAP not to miss the opportunity to bet!");
                return "error";
            }
            if (bet.getTeam().equals(gameService.findById(gameId).getTeam1())) {
                bet.setCourse(BigDecimal.valueOf(gameService.findById(gameId).getCourseForTeam1()));
            } else {
                bet.setCourse(BigDecimal.valueOf(gameService.findById(gameId).getCourseForTeam2()));
            }
            bet.setUser(customUser.getUser());
            bet.setGame(gameService.findById(gameId));
            bet.setCreated(LocalDateTime.now());
            bet.setTotalAmount(bet.getAmount().multiply(bet.getCourse()));
            betService.save(bet);
            Wallet wallet = walletService.findWalletByUser(customUser.getUser());
            wallet.setBalance(wallet.getBalance().subtract(bet.getAmount()));
            Operation operation = new Operation();
            operation.setTitle("placed a bet");
            operation.setWallet(wallet);
            operation.setAmount(bet.getAmount());
            operation.setCreated(LocalDateTime.now());
            operationService.save(operation);
            walletService.save(wallet);
            return "redirect:/user/"+id+"/bets";
        }


}
