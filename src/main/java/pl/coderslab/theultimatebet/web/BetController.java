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

/**
 * Controller responsible for providing all the data related to {@link Bet} and connecting the services with the views.
 */
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

    /**
     * GET for showing all the {@link Bet} by the {@link User}
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @return the view with all the bets.
     */
        @GetMapping("/{id}/bets")
        public String allBets(@PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
            if (currentUser.getUser().getId() == id) {
                model.addAttribute("currentUser", currentUser);
                List<Bet> allBets = betService.findAllByUserId(id);
                model.addAttribute("allBets", allBets);
                return "AllBets";
            } else {
                return "redirect:/";
            }
        }


    /**
     * GET for showing all the active {@link Bet} by the {@link User}
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @return the view with all the active bets or redirects to home if user has no authorities to do it
     */
        @GetMapping("/{id}/bets/active")
        public String allBetsActive (@PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
            if (currentUser.getUser().getId() == id) {
                model.addAttribute("currentUser", currentUser);
                List<Bet> allBets = betService.findAllByUserIdAndResult(id, null);
                model.addAttribute("allBets", allBets);
                return "AllBetsActive";
            } else {
                return "redirect:/";
            }
        }

    /**
     * GET for showing all the finished {@link Bet} by the {@link User}
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @return the view with all the finished bets or redirects to home if user has no authorities to do it
     */
        @GetMapping("/{id}/bets/finished")
        public String allBetsFinished (@PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
            if (currentUser.getUser().getId() == id) {
                model.addAttribute("currentUser", currentUser);
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

    /**
     * GET for showing specific {@link Bet} by the {@link User}
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @param betId is the id of the specific {@link Bet}
     * @return the view with the bet or redirects to home if user has no authorities to do it
     */
        @GetMapping("/{id}/bets/{betId}")
        public String singleBet (@PathVariable Long id, @PathVariable Long betId, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
            if ((currentUser.getUser().getId() == id) && (betService.findById(betId).getUser().getId()==id)) {
                model.addAttribute("currentUser", currentUser);
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

    /**
     * GET for cancelling specific {@link Bet} by the {@link User}
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @param betId is the id of the specific {@link Bet}
     * @return the view with confirmation of cancelling the bet or redirects to home if user has no authorities to do it
     */
    @GetMapping("/{id}/bets/{betId}/cancel")
    public String cancelBet (@PathVariable Long id, @PathVariable Long betId, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if ((currentUser.getUser().getId() == id) && (betService.findById(betId).getUser().getId()==id)) {
            model.addAttribute("currentUser", currentUser);
            Bet bet = betService.findById(betId);
            model.addAttribute("bet", bet);
            model.addAttribute("id", id);
            return "cancelBet";
        } else {
            return "redirect:/";
        }
    }

    /**
     * POST for cancelling specific {@link Bet} by the {@link User} - needs an agree from the user.
     * @param id is the id of the {@link User}
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @param betId is the id of the specific {@link Bet}
     * @param agree is the boolean to confirm the cancellation
     * @return errors if the game is finished or redirects to the wallet
     */
    @Transactional
    @PostMapping("/{id}/bets/{betId}/cancel")
    public String cancelBet (@PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable Long betId, @RequestParam String agree, Model model) {
        model.addAttribute("currentUser", currentUser);
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

    /**
     * GET for add new {@link Bet} by the {@link User}
     * @param id is the id of the {@link User}
     * @param gameId is the {@link Game} that is the subject of the Bet
     * @param currentUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @param model used to provide the data to the view.
     * @return view to add bet or redirects to home if user has no authorities to do it
     */
        @GetMapping("/{id}/bets/{gameId}/addBet")
        public String addBet (@PathVariable Long id, @PathVariable Long gameId, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
            if (currentUser.getUser().getId() == id) {
                model.addAttribute("currentUser", currentUser);
                model.addAttribute("id", id);
                Game game = gameService.findById(gameId);
                List<Team> teams = new ArrayList<>();
                teams.add(game.getTeam1());
                teams.add(game.getTeam2());
                User user = userService.findById(id);
                Wallet wallet = user.getWallet();
                model.addAttribute("wallet", wallet);
                model.addAttribute("teams", teams);
                model.addAttribute("game", game);
                Bet bet = new Bet();
                bet.setGame(game);
                bet.setUser(currentUser.getUser());
                model.addAttribute("bet", bet);
                return "addBet";
            } else {
                return "redirect:/";
            }
        }

    /**
     *
     * @param bet is the {@link Bet} to be placed
     * @param result {@link BindingResult}
     * @param id is the id of the {@link User}
     * @param gameId is the {@link Game} that is the subject of the Bet
     * @param model used to provide the data to the view in case of errors
     * @param logedUser keeping all the info about actual {@link User}, used to authenticate and provide the right authorities.
     * @return AddBet view if some errors, error if other errors related to placing bets, and redirects to all bets if everything is fine
     */
        @Transactional
        @PostMapping("/{id}/bets/{gameId}/addBet")
        public String addBet(@Valid @ModelAttribute Bet bet, BindingResult result,
                             @PathVariable Long id, @PathVariable Long gameId, Model model,
                             @AuthenticationPrincipal CurrentUser logedUser) {

            User currentUser = userService.findById(logedUser.getUser().getId());

            model.addAttribute("currentUser", currentUser);
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
            if (bet.getAmount().compareTo(BigDecimal.ZERO)==-1 || bet.getAmount().compareTo(BigDecimal.ZERO)==0) {
                model.addAttribute("info3", "Bet value must be bigger than 0! Try again with another amount!");
                return "error";
            }
            if (currentUser.getWallet().getBalance().compareTo(bet.getAmount())==-1) {
                model.addAttribute("info2", "You don't have enough money. " +
                        "Go back to bet to select another amount or add some money to your account ASAP not to miss the opportunity to bet!");
                return "error";
            }
            if (bet.getTeam().equals(gameService.findById(gameId).getTeam1())) {
                bet.setCourse(BigDecimal.valueOf(gameService.findById(gameId).getCourseForTeam1()));
            } else {
                bet.setCourse(BigDecimal.valueOf(gameService.findById(gameId).getCourseForTeam2()));
            }
            bet.setUser(currentUser);
            bet.setGame(gameService.findById(gameId));
            bet.setCreated(LocalDateTime.now());
            bet.setTotalAmount(bet.getAmount().multiply(bet.getCourse()));
            betService.save(bet);
            Wallet wallet = walletService.findWalletByUser(currentUser);
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
