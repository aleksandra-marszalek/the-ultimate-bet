package pl.coderslab.theultimatebet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.theultimatebet.CurrentUser;
import pl.coderslab.theultimatebet.entity.*;
import pl.coderslab.theultimatebet.service.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/user")
public class userAccountController {

    @Autowired
    WalletService walletService;

    @Autowired
    UserService userService;

    @Autowired
    OperationService operationService;

    @Autowired
    GameService gameService;

    @Autowired
    WithdrawalService withdrawalService;

    @GetMapping("/{id}")
    public String userAccount (@PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser.getUser().getId() == id) {
            model.addAttribute("id", id);
            return "UserAccount";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/{id}/wallet")
    public String showWallet (@PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser.getUser().getId() == id) {
            model.addAttribute("currentUser", currentUser);
            Wallet wallet = walletService.findWalletByUser(currentUser.getUser());
            model.addAttribute("wallet", wallet);
            List<Operation> lastOps = operationService.findAllByWalletLast(wallet);
            model.addAttribute("operations", lastOps);
            model.addAttribute("id", id);
            BigDecimal bg = new BigDecimal("5");
            if (wallet.getBalance().compareTo(bg)==-1) {
                model.addAttribute("info", "Wow, you're account lacks money! Don't hesitate and recharge it now!");
            }
            return "UserWallet";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/{id}/wallet/withdrawMoney")
    public String withdrawMoney (@PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser.getUser().getId() == id) {
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("id", id);
            Withdrawal withdrawal = new Withdrawal();
            model.addAttribute("withdrawal", withdrawal);
            return "WithdrawMoney";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/{id}/wallet/withdrawMoney")
    public String withdrawMoney (@Valid @ModelAttribute Withdrawal withdrawal, BindingResult result,
                                 @PathVariable Long id, Model model, @AuthenticationPrincipal CurrentUser logedUser) {
        User currentUser = userService.findById(logedUser.getUser().getId());
        if (result.hasErrors()) {
            return "WithdrawMoney";
        } if (withdrawal.getAmount().compareTo(BigDecimal.ZERO) == -1 || withdrawal.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            model.addAttribute("info3", "Withdrawal value must be bigger than 0! Try again with another amount!");
            return "error";
        }
        if (currentUser.getWallet().getBalance().compareTo(withdrawal.getAmount()) == -1) {
            model.addAttribute("info2", "You don't have enough money. " +
                    "Go back to select another amount");
            return "error";
        }

        Wallet wallet = walletService.findWalletByUser(currentUser);
        wallet.setBalance(wallet.getBalance().subtract(withdrawal.getAmount()));
        Operation operation = new Operation();
        operation.setTitle("withdraw money");
        operation.setWallet(wallet);
        operation.setAmount(withdrawal.getAmount());
        operation.setCreated(LocalDateTime.now());
        withdrawal.setWallet(wallet);
        withdrawalService.save(withdrawal);
        operationService.save(operation);
        walletService.save(wallet);
        return "redirect:/user/"+id+"/wallet";
    }



    @GetMapping("/{id}/wallet/addMoney/{amount}")
    public String addMoney (@PathVariable Long id, @PathVariable Integer amount, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (currentUser.getUser().getId() == id) {
            model.addAttribute("amount", amount);
            model.addAttribute("id", id);
            return "addMoney";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/{id}/wallet/addMoney/{amount}")
    public String addMoney (@PathVariable Long id, @PathVariable Integer amount, @RequestParam String agree, @AuthenticationPrincipal CurrentUser currentUser) {
        if (agree.equals("yes")) {
            User user = userService.findById(id);

            BigDecimal amountDecimal = BigDecimal.valueOf(amount);

            Wallet wallet = user.getWallet();
            wallet.setBalance( wallet.getBalance().add(amountDecimal) );

            user.setWallet( wallet );

//            Wallet wallet = walletService.findWalletByUser(user);
//            wallet.setBalance(wallet.getBalance().add(amountDecimal));
//            walletService.save(wallet);
            userService.save(user);

            Operation operation = new Operation();
            operation.setCreated(LocalDateTime.now());
            operation.setAmount(amountDecimal);
            operation.setWallet( wallet) ;
            operation.setTitle("add money");
            operationService.save(operation);
        }
        return "redirect:/user/"+id+"/wallet";
    }


}
