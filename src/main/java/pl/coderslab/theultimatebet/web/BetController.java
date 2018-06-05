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
//                model.addAttribute("games", gameService.findAllByStatus(0));
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
            if (result.hasErrors()) {
                return "addBet";
            }
            if (gameId==1) {
                model.addAttribute("info", "This game has already finished. You cannot place a bet anymore. " +
                        "Use menu to go back to another games.");
                return "addBet";
            }
            if (customUser.getUser().getWallet().getBalance().compareTo(bet.getAmount())<0) {
                model.addAttribute("info2", "You don't have enough money. " +
                        "Select another amount or add some money to your account now!");
                return "addBet";
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


//    @GetMapping("/{id}/bets)
//    public String allBets (@PathVariable Long id, @PathVariable Integer amount, @AuthenticationPrincipal CurrentUser customUser, Model model) {
//        if (customUser.getUser().getId() == id) {
//            model.addAttribute("amount", amount);
//            model.addAttribute("id", id);
//            return "addMoney";
//        } else {
//            return "redirect:/";
//        }
//    }
//
//    @PostMapping("/{id}/wallet/addMoney/{amount}")
//    public String addMoney (@PathVariable Long id, @PathVariable Integer amount, @RequestParam String agree) {
//        if (agree.equals("yes")) {
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
//        }
//        return "redirect:/user/"+id+"/wallet";
//    }
}
